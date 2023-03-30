#encoding:utf-8

module Deepspace
    class Damage
        #tenemos que poner new como privado?? (como en el ejemplo de la diap 14 del tema 3)
        #me ha dicho el profesor que si
        def initialize(w,s, wl)     #los dos primeros son int (numero de weapons y shields), el tercero es un array
            @nWeapons = w
            @nShields = s
            @weapons = wl
        end

        attr_reader     :nWeapons
        attr_reader     :nShields
        attr_reader     :weapons

        def self.newNumericWeapons(w,s)
            Damage.new(w, s, nil)
        end

        def self.newSpecificWeapons(wl, s)
            Damage.new(wl.length, s, wl)    #el numero wl.lenght en realidad no se va a usar en este tipo de weapon
        end

        private_class_method    :new

        def self.newCopy(d)
            if d.weapons == nil
                newNumericWeapons(d.nWeapons, d.nShields)
            else newSpecificWeapons(d.weapons.dup, d.nShields)      #se envia un duplicado
            end
        end

        def getUIversion
            return DamageToUI.new(self)
        end

        private
        def arrayContainsType(w, t)     #w array de weapons y t weapontype. devuelve un int
            error = -1
            pos = 0

            w.each{ |weap|
                if weap.type == t
                    return pos
                else pos += 1    
                end
            }

            return error #si no lo ha encontrado devuelve -1
        end

        public
        def adjust(w, s)    #w array de weapons, s array de shieldboosters. devuelve un damage
            shmin = [@nShields,s.length].min
            weapmin = [@nWeapons,w.length].min

            if ((@weapons != nil) && (!@weapons.empty?))
                aux1 = []
                aux2 = []

                @weapons.each{ |weaptype|
                    aux1.push(weaptype)
                }

                w.each{|weap|
                    aux2.push(weap)
                }

                result = Array.new

                @weapons.each{ |weaptype2|
                    ind = arrayContainsType(aux2,weaptype2)
                    if ind != -1
                        aux2.delete_at(ind)
                        result.push(weaptype2)
                    end
                    aux1.delete(weaptype2)
                }

                return Damage.newSpecificWeapons(result,shmin)
            end

            return Damage.newNumericWeapons(weapmin,shmin)
        end

        def discardWeapon(w)    #w es un weapon (no weapontype)
            aux = @weapons.index(w.type)
            if ((@nWeapons > 0) && (aux == nil))
                @nWeapons -= 1
            elsif aux != nil
                @weapons.delete_at(aux)
            end
        end

        def discardShieldBooster
            if(@nShields > 0)
                @nShields-=1;
            end
        end

        def hasNoEffect()   #devuelve booleano
            if @weapons == nil
                return @nShields == 0 && @nWeapons == 0
            end

            return @nShields == 0 && @weapons.empty?
        end

        def to_s
            if weapons != nil
                we = "["
                @weapons.each { |w|
                    if w == WeaponType::LASER
                        we += " LASER"
                    elsif w == WeaponType::MISSILE
                        we += " MISSILE"
                    else
                        we += " PLASMA"
                    end
                    we += ","
                }
                we += "]"
            end
            return "NShields: #{@nShields}, NWeapons: #{@nWeapons}, Weapons: " + we
        end
    end
end