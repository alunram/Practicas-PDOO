#encoding:utf-8

require_relative 'WeaponToUI'

module Deepspace
    class Weapon
        def initialize(na, ty, us) #string, weapontype, int
            @name = na
            @type = ty
            @uses = us
        end

        ####################################
        #no estoy seguro de si es asi
        def type
            @type
        end
        ######################################

        #no estoy seguro de si hace falta: si, para el constructor de copia
        def name
            @name
        end

        def uses
            @uses
        end

        def power
            type.power
        end

        def self.newCopy(copia)
            Weapon.new(copia.name, copia.type, copia.uses)
        end

        def useIt
            if(uses > 0)
                @uses -= 1
                power
            else
                return 1.0
            end
        end

        def to_s
            if (type == Deepspace::WeaponType::LASER)
                t = "LASER"
            elsif (type == Deepspace::WeaponType::MISSILE)
                t = "MISSILE"
            else
                t = "PLASMA"
            end
            return "Name: #{@name}, Type: " + t + ", Uses: #{@uses}"
        end

        def getUIversion
            return WeaponToUI.new(self)
        end
    end
end