#encoding:utf-8

module Deepspace
    module WeaponType
        class Type
            def initialize (pot)
                @power = pot
            end

            def power
                @power
            end

            def to_s
                if (self == Deepspace::WeaponType::LASER)
                    t = "LASER"
                elsif (self == Deepspace::WeaponType::MISSILE)
                    t = "MISSILE"
                else
                    t = "PLASMA"
                end
                return t
            end
        end

        #las unicas instancias:
        LASER = Type.new(2.0)
        MISSILE = Type.new(3.0)
        PLASMA = Type.new(4.0)
        
    end
end