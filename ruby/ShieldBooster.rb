#encoding:utf-8

module Deepspace
    class ShieldBooster
        def initialize(na, bo, us) #string, float, int
            @name = na
            @boost = bo
            @uses = us
        end

        def self.newCopy(copia)
            ShieldBooster.new(copia.name, copia.boost, copia.uses)
        end

        def boost
            @boost
        end

        def uses
            @uses
        end

        ####################################################
        #el guion no lo pide, pero, ¿como creo el constructor de copia sin esto?
        def name
            @name
        end
        ####################################################

        def useIt
            if(uses > 0)
                @uses -= 1
                boost
            else
                return 1.0
            end
        end

        def to_s
            return "Name: #{@name}, Boost: #{@boost}, Uses: #{@uses}"
        end

        def getUIversion
            return ShieldToUI.new(self)
        end
    end
end
