#encoding: utf-8

require_relative 'SpaceStation'
require_relative 'PowerEfficientSpaceStationToUI'

module Deepspace
    class PowerEfficientSpaceStation < SpaceStation
        @@EFFICIENCYFACTOR=1.10
        def initialize(station)
            copy(station)
        end

        def fire
            return super*@@EFFICIENCYFACTOR
        end
      
        def protection
            return super*@@EFFICIENCYFACTOR
        end

        def getUIversion
            return PowerEfficientSpaceStationToUI.new(self)
        end

        def setLoot(loot)
            super
            if loot.efficient
              return Transformation::SPACECITY
            else
              return Transformation::NOTRANSFORM
            end
        end

        def to_s
            return "PowerEfficientSpaceStation: " + super
        end
    end
end

