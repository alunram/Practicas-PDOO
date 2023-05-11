#encoding-utf:8

require_relative 'Dice'
require_relative 'GameStateController'
require_relative 'CardDealer'
require_relative 'SpaceStation'
require_relative 'GameUniverseToUI'
require_relative 'GameCharacter'
require_relative 'CombatResult'

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
            ch = @dice.firstShot
            if (ch == GameCharacter::ENEMYSTARSHIP)
                fire = enemy.fire
                result = station.receiveShot(fire)
                if (result == ShotResult::RESIST)
                    fire = station.fire
                    result = enemy.receiveShot(fire)
                    enemyWins = (result == ShotResult::RESIST)
                else
                    enemyWins = true
                end
            else
                fire = station.fire
                result = enemy.receiveShot(fire)
                enemyWins = (result == ShotResult::RESIST)
            end

            if enemyWins
                s = station.speed
                moves = @dice.spaceStationMoves(s)
                if !moves
                    damage = enemy.damage
                    station.setPendingDamage(damage)
                    combatResult = CombatResult::ENEMYWINS
                else
                    station.move
                    combatResult = CombatResult::STATIONESCAPES
                end
            else
                aLoot = enemy.loot
                station.setLoot(aLoot)
                combatResult = CombatResult::STATIONWINS
            end
        
            @gameState.next(@turns,@spaceStations.length)
        
            return combatResult

        end

        public
        def combat
            state = @gameState.state
            if (state == GameState::BEFORECOMBAT || state == GameState::INIT)
                return combatGo(@currentStation,@currentEnemy)
            else
                return CombatResult::NOCOMBAT
            end
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
            state = @gameState.state
            size = names.length

            if state == Deepspace::GameState::CANNOTPLAY
                dealer = CardDealer.instance

                for i in (0..size-1)
                    supplies = dealer.nextSuppliesPackage
                    station = SpaceStation.new(names[i], supplies)

                    nh = @dice.initWithNHangars
                    nw = @dice.initWithNWeapons
                    ns = @dice.initWithNShields

                    lo = Loot.new(0, nw, ns, nh, 0)
                    station.setLoot(lo)
                
                    @spaceStations.push(station)
                end

                @currentStationIndex = @dice.whoStarts(size)
                @currentStation = @spaceStations[@currentStationIndex]
                @currentEnemy = dealer.nextEnemy
                @gameState.next(@turns,size)
            end
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
            state = @gameState.state

            if state == Deepspace::GameState::AFTERCOMBAT
                stationState = @currentStation.validState()
                if stationState
                    @currentStationIndex = (@currentStationIndex+1) % @spaceStations.length
                    @turns+=1
                    @currentStation=@spaceStations[@currentStationIndex]
                    @currentStation.cleanUpMountedItems

                    dealer=CardDealer.instance
                    @currentEnemy = dealer.nextEnemy
                    @gameState.next(@turns, @spaceStations.length)

                    return true
                end
                return false
            end
            return false
        end

        def to_s
            if state == Deepspace::GameState::INIT
              state_ = "state:=INIT\n"
            elsif state == Deepspace::GameState::CANNOTPLAY
              state_ = "state:=CANNOTPLAT\n"
            elsif state == Deepspace::GameState::BEFORECOMBAT
              state_ = "state:=BEFORECOMBAT\n"
            else
              state_ = "state:=AFTERCOMBAT\n"
            end
      
            actualEnemy = "CURRENTENEMY:=nil\n"
            if @currentEnemy != nil
              actualEnemy = "CURRENTENEMY:\n << #{@currentEnemy.to_s}"
            end
      
            dice_ = "DICE:\n #{@dice.to_s}"
      
            actualStation = "CURRENTSTATION:=nil\n"
            if @currentStation != nil
              actualStation = "CURRENTSTATION:\n #{@currentStation.to_s}"
            end
      
            stations = "SPACESTATIONS: \n #{@spaceStations.to_s.inspect}"
      
            return "INDEX: " + "#{@currentStationIndex}" + "\n"+ "TURNS: " + "#{@turns}" + "\n" + state_ + actualEnemy + actualStation + dice_ + stations + "\n"
          end
    end
end