# encoding: utf-8

require_relative 'Dice'
require_relative 'PowerEfficientSpaceStation'
require_relative 'BetaPowerEfficientSpaceStationToUI'

module Deepspace
    class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation
        @@EXTRAEFFICIENCY=1.2
        def initialize(station)
            super
            @dice = Dice.new
        end
    
        def fire
            if @dice.extraEfficiency
            return super*@@EXTRAEFFICIENCY
            else
            return super
            end
        end
    
        def getUIversion
            return BetaPowerEfficientSpaceStationToUI.new(self)
        end
    
        def to_s
            return "Beta" + super
        end
    end
end


