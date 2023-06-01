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
public class SpaceCity extends SpaceStation{
    private SpaceStation base;
    private ArrayList<SpaceStation> collaborators;
    
    SpaceCity(SpaceStation base, ArrayList<SpaceStation> rest){
        super(base);
        
        this.base = base;
        collaborators = new ArrayList<SpaceStation>(rest);        
    }
    
    public ArrayList<SpaceStation> getCollaborators(){
        return collaborators;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        
        return Transformation.NOTRANSFORM;
    }
    
    @Override
    public float fire(){
        float result = super.fire();
        
        for(int i=0; i<collaborators.size(); i++){
            result += collaborators.get(i).fire();
        }
        
        return result;
    }
    
    @Override
    public float protection(){
        float result = super.protection();
        
        for(int i=0; i<collaborators.size(); i++){
            result += collaborators.get(i).protection();
        }
        
        return result;
    }
    
    @Override
    public SpaceCityToUI getUIversion(){
        return new SpaceCityToUI(this);
    }
    
    @Override
    public String toString(){
        String bas = "SPACE CITY: \n" + "Base: " + base.toString() +"\n";
        String col = "Collaborators: \n";
        
        for (int i=0; i<collaborators.size();i++){
            col += collaborators.get(i).toString() + "\n";
        }
        
        String result = bas + col;
        
        return result;
    }
    
}
