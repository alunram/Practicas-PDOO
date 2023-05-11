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
public class Hangar {
    private int maxElements;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;
    
    Hangar(int capacity){
        maxElements = capacity;
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
    }
    
    Hangar(Hangar h){
        //maxElements = h.maxElements;
        this(h.maxElements);
        
        for(Weapon w : h.weapons){
            weapons.add(w);
        }
        for(ShieldBooster sb : h.shieldBoosters){
            shieldBoosters.add(sb);
        }
    }
    
    HangarToUI getUIversion(){
        return new HangarToUI(this);
    }
    
    private boolean spaceAvailable(){
        if(maxElements > (weapons.size() + shieldBoosters.size())){
            return true;
        }else return false;
    }
    
    public boolean addWeapon(Weapon w){
        if(spaceAvailable()){
            weapons.add(w);
            return true;
        }else 
            return false;
    }
    
    public boolean addShieldBooster(ShieldBooster s){
        if(spaceAvailable()){
            shieldBoosters.add(s);
            return true;
        }else 
            return false;
    }
    
    public int getMaxElements(){
        return maxElements;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    public ShieldBooster removeShieldBooster(int s){
        if(s>=0 && s<shieldBoosters.size()){
            ShieldBooster sb = shieldBoosters.remove(s);
            return sb;
        }
        return null;
    }
    
    public Weapon removeWeapon(int w){
        if(w >= 0 && w < weapons.size()){
            Weapon arma = weapons.remove(w);
            return arma;
        }
        return null;
    }  
    
    @Override
    public String toString(){
        String e = "Maximo de elementos del hangar: " + this.getMaxElements() + "\n";
        String w = "Weapons del hangar: \n";
        for(int i=0;i<weapons.size(); i++){
            Weapon aux;
            aux = this.getWeapons().get(i);
            w = w + " " + aux.toString();
        }
        
        String s = "Shieldboosters del hangar: \n";
        for(int i=0;i<shieldBoosters.size(); i++){
            ShieldBooster aux;
            aux = this.getShieldBoosters().get(i);
            s = s + " " + aux.toString();
        }
        
        String last = e + w + s;
        
        return last;
    }
}
