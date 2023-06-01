#encoding:utf-8

require_relative 'Damage'
require_relative 'NumericDamageToUI'

module Deepspace
    class NumericDamage < Damage

        public_class_method :new

        def initialize(nw,s)
            super(s)
            @nWeapons = nw
        end

        attr_reader :nWeapons

        def hasNoEffect
            return (@nWeapons == 0 && super)
        end

        def copy
            return NumericDamage.new(@nWeapons,nShields)
        end
      
        def getUIversion
            return NumericDamageToUI.new(self)
        end

        def adjust(w,s)
            #shmin = [@nShields,s.length].min ##aqui se pondria super?¿?¿?
            shmin = super(s)
            weapmin = [@nWeapons,w.length].min
      
            return NumericDamage.new(weapmin,shmin)
        end

        def discardWeapon(w)
            if @nWeapons > 0
                @nWeapons -= 1
            end
        end

        def to_s
            return "Numeric Damage. NShields: #{@nShields}, NWeapons: #{@nWeapons}"
        end
    end
end




