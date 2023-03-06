#encoding:utf-8

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
    end
end