#encoding:utf-8

require_relative 'Damage'
require_relative 'SpecificDamageToUI'

module Deepspace
    class SpecificDamage < Damage

        public_class_method :new

        def initialize(w, s)
            super(s)
            @weapons=w
        end

        attr_reader :weapons

        def hasNoEffect
            return (super && @weapons.length == 0)
        end
      
        def copy
            return SpecificDamage.new(@weapons,nShields)
        end
      
        def getUIversion
            return SpecificDamageToUI.new(self)
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
        #se ajusta a la situacion del jugador, para que no se quede pillado el juego. pediria descartar algo que no tienes!
        def adjust(w, s)    #w array de weapons, s array de shieldboosters (del jugador). devuelve un damage
            #shmin = [@nShields,s.length].min ###aqui se pondria super?¿?¿?¿
            shmin = super(s)
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

                return SpecificDamage.new(result,shmin)
            
        end

        def discardWeapon(w)
            if(@weapons.length != 0)
              index = @weapons.index(w.type)
              if index != nil
                @weapons.delete_at(index)
              end
            end
        end

        def to_s
            we = "vacio"
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
            return "Specific Damage. NShields: #{@nShields}, Weapons: " + we
        end

    end
end

