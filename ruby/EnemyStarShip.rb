#encoding-utf: 8

module Deepspace
    class EnemyStarShip
        def initialize(n, a, s, l, d)   #string, float, float, loot, damage
            @name = n
            @ammoPower = a
            @shieldPower = s
            @loot = l
            @damage = d
        end

        def self.newCopy(e)
            EnemyStarShip.new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
        end

        def getUIversion
            return EnemyToUI.new(self)
        end

        attr_reader :name
        attr_reader :ammoPower
        attr_reader :shieldPower
        attr_reader :loot
        attr_reader :damage

        def protection
            @shieldPower
        end

        def fire
            @ammoPower
        end

        def receiveShot(shot)
            if protection < shot
                return ShotResult::DONOTRESIST
            else return ShotResult::RESIST
            end
        end

        def to_s
            l = @loot.to_s
            d = @damage.to_s
            return "Name: #{@name}, AmmoPower: #{@ammoPower}, ShieldPower: #{@shieldPower}, Loot: " + l + "Damage: " + d
        end
    end
end