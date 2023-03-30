#encoding-utf:8

module Deepspace
   class SpaceStation
        @@MAXFUEL = 100             #maxima cantidad de combustible
        @@SHIELDLOSSPERUNIT = 0.1   #unidades de escudo que se pierden por unidad de potencia de disparo recibido
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

        private:
        def assignFuelValue(f)
            if ( f > @@MAXFUEL)
                @fuelUnits = @@MAXFUEL
              else
                @fuelUnits = f
        end

        def cleanPendingDamage
            if( @pendingDamage.hasNoEffect )
                @pendingDamage = nil
              end
        end

        public:
        def cleanUpMpuntedItems #elimina elementos con 0 usos
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

        def discardHangar
            @hangar = nil
        end

        def discardShieldBooster(i) #i int
            
        end

        def discardShieldBoosterInHangar #i int
            if (@hangar != nil)
                @hangar.removeShieldBooster(i)
        end

        def discardWeapon(i) #i int
            
        end

        def discardWeaponInHangar(i)
            if (@hangar != nil)
                @hangar.removeWeapon(i)
        end

        def fire
            
        end

        attr_reader :ammoPower
        attr_reader :fuelUnits
        attr_reader :hangar
        attr_reader :name
        attr_reader :nMedals
        attr_reader :pendingDamage
        attr_reader :shieldBoosters
        attr_reader :shieldPower

        def speed
            return @fuelUnits.to_f/@@MAXFUEL.to_f
        end

        def getUIversion
            return SpaceStationToUI.new(self)
        end

        def mountShieldBooster(i)
            if (@hangar != nil && i>=0 && i<@hangar.shieldBoosters.length)
                sb = @hangar.removeWeapon(i)
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
            
        end

        def receiveHangar(h)
            if (@hangar == nil)
                @hangar = h
              end
        end

        def receiveShieldBooster(s)
            if (@hangar != nil)
                return @hangar.addShieldBooster(_shield)
              else
                false
              end
        end

        def receiveShot(shot) #shot es float
            
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