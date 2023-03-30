#encoding:utf-8

module Deepspace
    class Dice
        def initialize #son float constantes, menos generator
            @NHANGARSPROB = 0.25
            @NSHIELDSPROB = 0.25
            @NWEAPONSPROB = 0.33
            @FIRSTSHOTPROB = 0.5
            @generator = rand()
        end

        #Este método determina el número de hangares que recibirá una estación espacial al ser creada
        def initWithNHangars
            if @generator < @NHANGARSPROB   
                return 0
            else
                return 1
            end
        end

        #Este método determina el número de armas que recibirá una estación espacial al ser creada
        def initWithNWeapons
            aux = @generator
            if aux < @NWEAPONSPROB   
                return 1
            elsif aux < 2*@NWEAPONSPROB
                return 2
            else return 3
            end
        end

        #Este método determina el número de potenciadores de escudo que recibirá una estación espacial al ser creada
        def initWithNShields
            if @generator < @NSHIELDSPROB   
                return 0
            else
                return 1
            end
        end

        def whoStarts(nPlayers) #nPlayers es un int
            rand(nPlayers)
        end

        def firstShot
            if @generator < @FIRSTSHOTPROB   
                return GameCharacter::SPACESTATION
            else
                return GameCharacter::ENEMYSTARSHIP
            end
        end

        def spaceStationMoves(speed) #speed es un float, suponemos que entre 0 y 1. la probabilidad de moverse es mayor cuanto mas velocidad tenga
            if @generator < speed   
                return true #se movera
            else
                return false #no se movera
            end
        end

        def to_s
            return "NHangarsProb: #{@NHANGARSPROB}, NShieldsProb: #{@NSHIELDSPROB}, NWeaponsProb: #{@NWEAPONSPROB}, FirstShotProb: #{@FIRSTSHOTPROB}, Generator: #{@generator}"
        end
    end
end
