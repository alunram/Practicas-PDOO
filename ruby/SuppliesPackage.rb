#encoding:utf-8

module Deepspace
    class SuppliesPackage
        def initialize(amm, fue, shi)
            @ammoPower = amm
            @fuelUnits = fue
            @shieldPower = shi
        end

        def ammoPower
            @ammoPower
        end

        def fuelUnits
            @fuelUnits
        end

        def shieldPower
            @shieldPower
        end

        def self.newCopy(copia)
            SuppliesPackage.new(copia.ammoPower, copia.fuelUnits,copia.shieldPower)
        end

        def to_s
            return "AmmoPower: #{@ammoPower}, FuelUnits: #{@fuelUnits}, ShieldPower: #{@shieldPower}"
        end
    end
end
