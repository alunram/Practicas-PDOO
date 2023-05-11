#encoding:utf-8

require_relative 'LootToUI'

module Deepspace
    class Loot
        def initialize(sup, wea, shi, han, med) #son enteros
            @nSupplies = sup
            @nWeapons = wea
            @nShields = shi
            @nHangars = han
            @nMedals = med
        end

        def nSupplies
            @nSupplies
        end

        def nWeapons
            @nWeapons
        end

        def nShields
            @nShields
        end

        def nMedals
            @nMedals
        end

        def nHangars
            @nHangars
        end

        def to_s
            return "NSupplies: #{@nSupplies}, nWeapons: #{@nWeapons}, nShields: #{@nShields}, nHangars: #{@nHangars}, nMedals: #{@nMedals}"
        end

        def getUIversion
            return LootToUI.new(self)
        end
    end
end