#encoding-utf:8

require_relative 'SpaceStationToUI'
require_relative 'CardDealer'
require_relative 'Transformation'

module Deepspace
   class SpaceStation
        @@MAXFUEL = 100             #maxima cantidad de combustible
        @@SHIELDLOSSPERUNITSHOT = 0.1   #unidades de escudo que se pierden por unidad de potencia de disparo recibido
        def initialize(n, s) #string, suppliespackage
            @name = n
            @ammoPower = s.ammoPower           #float
            @nMedals = 0                        #int
            @fuelUnits =  s.fuelUnits          #float
            @shieldPower = s.shieldPower         #float
            @pendingDamage = nil
            @hangar = nil
            @weapons  = Array.new
            @shieldBoosters = Array.new
        end

        private
        def assignFuelValue(f)
            if ( f > @@MAXFUEL)
                @fuelUnits = @@MAXFUEL
            else
                @fuelUnits = f
            end
        end

        def cleanPendingDamage
            if( @pendingDamage.hasNoEffect )
                @pendingDamage = nil
            end
        end

        public
        def cleanUpMountedItems #elimina elementos con 0 usos
            wnu = []
            snu = []
            @weapons.each{ |w|
                if w.uses == 0
                    wnu.push(w)
                end
            }
            wnu.each{ |wNoUses|
                @weapons.delete(wNoUses)
            }
            @shieldBoosters.each { |sb|
                if sb.uses == 0
                    snu.push(sb)
                end
            }
            snu.each{ |sbNoUses|
                @shieldBoosters.delete(sbNoUses)
            }
        end

        def copy(s)
            @ammoPower = s.ammoPower
            @name = s.name
            @shieldPower = s.shieldPower
            @fuelUnits = s.fuelUnits
            @nMedals = s.nMedals

            @pendingDamage = s.pendingDamage
            @weapons = s.weapons
            @shieldBoosters = s.shieldBoosters
            @hangar = s.hangar
        end

        def discardHangar
            @hangar = nil
        end

        def discardShieldBooster(i) #i int
            size = @shieldBoosters.length
            if ( i>=0 && i<size)
                s = @shieldBoosters.delete_at(i)
                if @pendingDamage != nil
                    @pendingDamage.discardShieldBooster
                    cleanPendingDamage
                end
            end
        end

        def discardShieldBoosterInHangar(i) #i int
            if (@hangar != nil)
                @hangar.removeShieldBooster(i)
            end
        end

        def discardWeapon(i) #i int
            size = @weapons.length
            if ( i>=0 && i<size)
                w = @weapons.delete_at(i)
                if @pendingDamage != nil
                    @pendingDamage.discardWeapon(w)
                    cleanPendingDamage
                end
            end
        end

        def discardWeaponInHangar(i)
            if (@hangar != nil)
                @hangar.removeWeapon(i)
            end
        end

        def fire
            size = @weapons.length
            factor = 1.0

            for i in (0..size-1)
                w = @weapons.at(i)
                factor *= w.useIt
            end

            return ammoPower*factor
        end

        attr_reader :ammoPower
        attr_reader :fuelUnits
        attr_reader :hangar
        attr_reader :name
        attr_reader :nMedals
        attr_reader :pendingDamage
        attr_reader :shieldBoosters
        attr_reader :weapons
        attr_reader :shieldPower

        def speed
            return @fuelUnits.to_f/@@MAXFUEL.to_f
        end

        def getUIversion
            return SpaceStationToUI.new(self)
        end

        def mountShieldBooster(i)
            if (@hangar != nil && i>=0 && i<@hangar.shieldBoosters.length)
                sb = @hangar.removeShieldBooster(i)
                if(sb != nil)
                  @shieldBoosters.push(sb)
                end
            end
        end

        def mountWeapon(i)
            if (@hangar != nil && i>=0 && i<@hangar.weapons.length)
                #weapon = @hangar.weapons[i]
                weapon = @hangar.removeWeapon(i)
                if(weapon != nil)
                  @weapons.push(weapon)
                end
            end
        end

        def move
            @fuelUnits -= (speed*fuelUnits)
            if @fuelUnits<0
                @fuelUnits = 0
            end
        end

        def protection
            size = @shieldBoosters.length
            factor = 1.0

            for i in (0..size-1)
                s = @shieldBoosters.at(i)
                factor *= s.useIt
            end
            
            return shieldPower*factor
        end

        def receiveHangar(h)
            if (@hangar == nil)
                @hangar = h
            end
        end

        def receiveShieldBooster(s)
            if (@hangar != nil)
                return @hangar.addShieldBooster(s)
              else
                false
            end
        end

        def receiveShot(shot) #shot es float
            myProtection = self.protection
            if(myProtection>=shot)
                @shieldPower -= @@SHIELDLOSSPERUNITSHOT * shot
                @shieldPower = [0.0,@shieldPower].max
                return Deepspace::ShotResult::RESIST
            else
                @shieldPower = 0.0
                return Deepspace::ShotResult::DONOTRESIST
            end

        end

        def receiveSupplies(s)
            @ammoPower += s.ammoPower
            @fuelUnits += s.fuelUnits
            @shieldPower += s.shieldPower

            if(@fuelUnits > @@MAXFUEL)
                @fuelUnits = @@MAXFUEL
            end
        end

        def receiveWeapon(w)
            if(hangar != nil)
                return @hangar.addWeapon(w)
              else return false
            end
        end

        def setLoot(loot)
            dealer = Deepspace::CardDealer.instance
            h = loot.nHangars

            if(h>0)
                hangar = dealer.nextHangar()
                receiveHangar(hangar)
            end

            elements = loot.nSupplies
            for i in (1..elements)
                sup = dealer.nextSuppliesPackage
                receiveSupplies(sup)
            end

            elements = loot.nWeapons
            for i in (1..elements)
                weap = dealer.nextWeapon
                receiveWeapon(weap)
            end

            elements = loot.nShields
            for i in (1..elements)
                sh = dealer.nextShieldBooster
                receiveShieldBooster(sh)
            end

            medals = loot.nMedals
            @nMedals += medals

            if loot.efficient
                return Deepspace::Transformation::GETEFFICIENT
            end 
            if loot.spaceCity
                return Deepspace::Transformation::SPACECITY
            end
    
            return Deepspace::Transformation::NOTRANSFORM
        end

        def setPendingDamage(d)
            @pendingDamage = d.adjust(@weapons, @shieldBoosters)
            cleanPendingDamage
        end

        def validState
            if @pendingDamage == nil
                return true
            end

            return @pendingDamage.hasNoEffect
        end

        def to_s
            pd = ", Pending Damage: "
            if pendingDamage != nil
                pd += "#{@pendingDamage.to_s}"
            else    pd += "nil"
            end

            we = ", Weapons mounted: "
            if (!@weapons.empty?)
                @weapons.each { |aux|
                    we += "#{aux.to_s}"
                }
            end

            sh = ", Shield Boosters mounted: "
            if (!@shieldBoosters.empty?)
                @shieldBoosters.each { |aux|
                    sh += "#{aux.to_s}"
                }
            end

            h = "Hangar:"
            if hangar != nil
                h += "#{@hangar.to_s}"
            else    h += "nil"
            end

            return "Name: #{@name}, Ammo Power: #{@ammoPower}, Fuel Units: #{@fuelUnits}, Shield Power: #{@shieldPower}, NMedals: #{@nMedals}" + pd + we + sh + h

        end
   end 
end