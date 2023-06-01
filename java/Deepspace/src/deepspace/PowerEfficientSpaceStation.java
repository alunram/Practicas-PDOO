/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author alvaro
 */
public class PowerEfficientSpaceStation extends SpaceStation{
    private final float EFFICIENCYFACTOR = 1.1f;
    
    PowerEfficientSpaceStation(SpaceStation station){
        super(station);
    }
    
    @Override
    public PowerEfficientSpaceStationToUI getUIversion(){
        return new PowerEfficientSpaceStationToUI(this);
    }
    
    @Override
    public float fire(){
        float result = super.fire()*EFFICIENCYFACTOR;
 
        return result;
    }
    
    @Override
    public float protection(){
        float result = super.protection()*EFFICIENCYFACTOR;
        
        return result;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        Transformation t = super.setLoot(loot);
        
        if ( t == Transformation.SPACECITY )
            return Transformation.NOTRANSFORM;
        else
            return t;
    }
    
    @Override
    public String toString(){
        return "POWER EFFICIENT SPACE STATION: \n" + super.toString();
    }
}
