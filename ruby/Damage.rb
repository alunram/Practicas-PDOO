#encoding:utf-8

require_relative 'DamageToUI'

module Deepspace
    class Damage
        def initialize(s)     #sera un int
            @nShields = s   #numero de escudos que pierdes
        end

        attr_reader     :nShields

        private_class_method    :new

        def getUIversion
            return DamageToUI.new(self)
        end

        #se ajusta a la situacion del jugador, para que no se quede pillado el juego. pediria descartar algo que no tienes!
        def adjust(s)    # s array de shieldboosters (del jugador). devuelve un damage
            shmin = [@nShields,s.length].min

            return shmin
        end

        def discardShieldBooster
            if(@nShields > 0)
                @nShields-=1;
            end
        end

        def hasNoEffect()   #devuelve booleano
            return @nShields == 0 
        end
    end
end