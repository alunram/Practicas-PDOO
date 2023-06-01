#encoding:utf-8

require_relative 'LootToUI'

module Deepspace
    class Loot
        def initialize(sup, wea, shi, han, med, ef=false, city=false) #seran enteros, menos los parametros nuevos de la P4
            @nSupplies = sup
            @nWeapons = wea
            @nShields = shi
            @nHangars = han
            @nMedals = med
            @getEfficient = ef
            @spaceCity = city
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

        def efficient
            return @getEfficient
        end
        
        def spaceCity
            return @spaceCity
        end

        def to_s
            result = "NSupplies: #{@nSupplies}, nWeapons: #{@nWeapons}, nShields: #{@nShields}, nHangars: #{@nHangars}, nMedals: #{@nMedals}"
            result += ", getEfficient: " + efficient.to_s + ", spaceCity: " + spaceCity.to_s
            return result
        end

        def getUIversion
            return LootToUI.new(self)
        end
    end
end