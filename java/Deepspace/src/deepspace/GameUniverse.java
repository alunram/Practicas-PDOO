/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;
import java.util.ArrayList;
/**
 *
 * @author alvaro
 */
public class GameUniverse {
    private static final int WIN = 10; 
    private int currentStationIndex;
    private int turns;
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations = new ArrayList<>();
    private GameStateController gameState;
    private EnemyStarShip currentEnemy;
    private Dice dice;
    
    public GameUniverse(){
        gameState = new GameStateController();
        turns = 0;
        dice = new Dice();
        
        currentStationIndex = 0;
        currentStation=null;
        currentEnemy=null;
    }
    
    private CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        GameCharacter ch = dice.firstShot();
        boolean enemyWins, moves;
        float fire, s;
        ShotResult result;
        Damage damage;
        CombatResult combatResult;
                
        if( ch == GameCharacter.ENEMYSTARSHIP){
            fire = enemy.fire();
            result = station.receiveShot(fire);
            if (result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
    
                enemyWins = (result == ShotResult.RESIST);
            }else{
                enemyWins = true;
            }
        }else{
            fire = station.fire();
            result = enemy.receiveShot(fire);
            enemyWins = (result == ShotResult.RESIST);
        }
        
        if (enemyWins){
            s = station.getSpeed();
            moves = dice.spaceStationMoves(s);
            if(!moves){
                damage = enemy.getDamage();
                station.setPendingDamage(damage);
                combatResult = CombatResult.ENEMYWINS;
            }else{
                station.move();
                combatResult = CombatResult.STATIONESCAPES;
            }
        }else{
            Loot aloot = enemy.getLoot();
            station.setLoot(aloot);
            combatResult = CombatResult.STATIONWINS;
        }
        
        gameState.next(turns, spaceStations.size());
        
        return combatResult;
    }
    
    public CombatResult combat(){
        GameState state = gameState.getState();
        if( (state==GameState.BEFORECOMBAT) || (state==GameState.INIT) )
            return combat(currentStation, currentEnemy);
        else
            return CombatResult.NOCOMBAT;
    }
    
    public void discardHangar(){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT)
            currentStation.discardHangar();
    }
    
    public void discardShieldBooster(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT)
            currentStation.discardShieldBooster(i);
    }
    
    public void discardShieldBoosterInHangar(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT)
            currentStation.discardShieldBoosterInHangar(i);
    }
    
    public void discardWeapon(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT)
            currentStation.discardWeapon(i);
    }
    
    public void discardWeaponInHangar(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT)
            currentStation.discardWeaponInHangar(i);
    }
    
    public GameState getState(){
         return gameState.getState();
    }
    
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(currentStation, currentEnemy);
    }
    
    public boolean haveAWinner(){
        if(currentStation.getNMedals() >= WIN)
            return true;
        else
            return false;
    }
    
    public void init(ArrayList<String> names){
        GameState state = getState();
        
         if(state==GameState.CANNOTPLAY){
            CardDealer dealer = CardDealer.getInstance();
             
            for(int i=0; i<names.size(); i++){
                SuppliesPackage supplies = dealer.nextSuppliesPackage();
                SpaceStation station = new SpaceStation(names.get(i), supplies);
            
                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();
                Loot l = new Loot(0,nw,ns,nh,0);
                
                station.setLoot(l);
                
                spaceStations.add(station);
            }
             
            currentStationIndex = dice.whoStarts(names.size());
            currentStation = spaceStations.get(currentStationIndex);
            currentEnemy = dealer.nextEnemy();
            
            gameState.next(turns, spaceStations.size());
         }
    }
    
    public void mountShieldBooster(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT)
            currentStation.mountShieldBooster(i);
    }
    
    public void mountWeapon(int i){
        if (getState() == GameState.INIT || getState() == GameState.AFTERCOMBAT)
            currentStation.mountWeapon(i);
    }   
    
    public boolean nextTurn(){
        GameState state = gameState.getState();
        
        if ( state == GameState.AFTERCOMBAT ){
            boolean stationState = currentStation.validState();
            if(stationState){
                currentStationIndex = (currentStationIndex+1)%spaceStations.size();
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();
                
                gameState.next(turns, spaceStations.size());
                
                return true;
            } else return false;
        } else return false;
    }
    
    @Override
    public String toString(){
        String actualStationInd = "Station Index:" + currentStationIndex + "\n";
        String turn = "Turns: " + turns + "\n";
        String gState = "GameState: " + gameState + "\n";
        String actualEnemy = "CurrentEnemy: null\n";
        if(currentEnemy != null){
            actualEnemy = "CurrentEnemy: \n" + currentEnemy.toString() + "\n\n";
        }
        String actualStation = "CurrentStation: null\n";
        if(currentStation != null){
            actualStation = "CurrentStation: \n" + currentStation.toString() + "\n\n";
        }
        //String dice = "Dice:  \n" + dice.toString() + "\n\n";
        
        String spaceStats = "SpaceStations: \n" + spaceStations.toString() + "\n\n";
        
        String result = actualStationInd + turn + gState + actualEnemy + actualStation  + spaceStats;
        
        return result;  
    }
    
}
