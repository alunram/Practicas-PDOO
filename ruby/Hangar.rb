#encoding:utf-8

module Deepspace
    class Hangar
        def initialize(capacity)    #es un int
            @maxElements = capacity
            @weapons = Array.new
            @shieldBoosters = Array.new
        end

        def self.newCopy(h)
            result = Hangar.new(h.maxElements)

            h.weapons.each{ |w|
                result.addWeapon(w)
            }

            h.shieldBoosters.each{ |s|
                result.addShieldBooster(s)
            }

            return result
        end

        def getUIversion
            return HangarToUI.new(self)
        end

        private #tambien se podria hacer asi: private: spaceAvailable
        def spaceAvailable
            return ((@maxElements - @weapons.length - @shieldBoosters.length) > 0)
        end

        public
        def maxElements
            @maxElements
        end

        def shieldBoosters
            @shieldBoosters
        end
        #otra forma: attr_reader:weapons    (reader o writer o accessor)
        def weapons
            @weapons
        end

        def addWeapon(w)
            if spaceAvailable
                @weapons.push(w)
                return true
            else return false
            end
        end

        def addShieldBooster(s)
            if spaceAvailable
                @shieldBoosters.push(s)
                return true
            else return false
            end
        end

        def removeShieldBooster(s)      #s es un int(la posicion), devuelve un shieldbooster
            if((s >= 0) && (s < @shieldBoosters.length))
                return @shieldBoosters.delete_at(s)     #delete_at en los arrays de ruby devuelve el elemento borrado
            else return nil
            end
        end

        def removeWeapon(w)     #w es un int(la posicion), devuelve un weapon
            if((s >= 0) && (s < @weapons.length))
                return @weapons.delete_at(s)
            else return nil
            end
        end

        def to_s
            we = ""
            if (!@weapons.empty?)
                @weapons.each { |aux|
                    we += "#{aux.to_s}"
                }
            end
            sh = ""
            if (!@shieldBoosters.empty?)
                @shieldBoosters.each { |aux|
                    sh += "#{aux.to_s}"
                }
            end
            return "MaxElements: #{@maxElements}, Weapons: " + we + ", Shields: " + sh
        end
    end
end