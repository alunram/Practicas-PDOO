#encoding-utf:8

module Deepspace
    class GameUniverse
        @@WIN = 10
        def initialize
            @turns = 0
            @currentStationIndex = 0
            @dice = Dice.new
            @gameState = GameStateController.new
            @currentStation = nil
            @currentEnemy = nil
            @spaceStations  = Array.new
        end

        def combatGo(station, enemy)
            
        end

        def combat
            
        end 

        def discardHangar
            if(state == GameState::INIT || state == GameState::AFTERCOMBAT)
                @currentStation.discardHangar
            end
        end

        def discardShieldBooster(i)
            if(state == GameState::INIT || state == GameState::AFTERCOMBAT)
                @currentStation.discardShieldBooster(i)
            end
        end

        def discardShieldBoosterInHangar(i)
            if(state == GameState::INIT || state == GameState::AFTERCOMBAT)
                @currentStation.discardShieldBoosterInHangar(i)
            end
        end

        def discardWeapon(i)
            if(state == GameState::INIT || state == GameState::AFTERCOMBAT)
                @currentStation.discardWeapon(i)
            end
        end

        def discardWeaponInHangar(i)
            if(state == GameState::INIT || state == GameState::AFTERCOMBAT)
                @currentStation.discardWeaponInHangar(i)
            end
        end

        def state
            return @gameState.state       #devuelve gameState o gameState.state?: segun el diagrama es lo segundo
        end

        def getUIversion
            return GameUniverseToUI.new(@currentStation, @currentEnemy)
        end

        def haveAWinner
            return @currentStation.nMedals >= @@WIN
        end

        def init(names) #array de strings
            
        end

        def mountShieldBooster(i)
            if(state == GameState::INIT || state == GameState::AFTERCOMBAT)
                @currentStation.mountShieldBooster(i)
            end
        end

        def mountWeapon(i)
            if(state == GameState::INIT || state == GameState::AFTERCOMBAT)
                @currentStation.mountWeapon(i)
            end
        end

        def nextTurn
            
        end
    end
end