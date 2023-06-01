#encoding-utf:8

require_relative 'SpaceStation'
require_relative 'SpaceCityToUI'

module Deepspace
    class SpaceCity < SpaceStation
        def initialize(bas, rest) #base sera una spacestation y rest un array de spacestation
            copy(bas)
            @base = bas
            @collaborators = rest
        end

        attr_reader :collaborators

        def fire
            dam =  super
            @collaborators.each do |stations|
              dam += stations.fire
            end
            return dam
        end

        def protection
            sh =  super
            @collaborators.each do |stations|
              sh += stations.protection
            end
            return sh
        end

        def setLoot(loot)
            super
            return Transformation::NOTRANSFORM
        end

        def getUIversion
            return SpaceCityToUI.new(self)
        end

        def to_s
            b = "Base: " + @base.to_s
            c = "Colaboradores: "
      
            @collaborators.each { |colab|
              c += colab.to_s + "\n"
            }
            
            return b + c
        end
    end
end

