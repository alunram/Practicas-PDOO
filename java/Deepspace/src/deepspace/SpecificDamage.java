/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author alvaro
 */
public class SpecificDamage extends Damage{
    static private final int NOUSE = -1;
    private ArrayList<WeaponType> weapons = new ArrayList<>();
    
    SpecificDamage(ArrayList<WeaponType> wl, int s){
        super(s);
        weapons = new ArrayList<>(wl);
    }
    
    SpecificDamage(SpecificDamage dam){
        this(dam.getWeapons(), dam.getNShields());
    }
    
    public ArrayList<WeaponType> getWeapons(){
        return weapons;
    }
    
    @Override
    public SpecificDamage copy(){
        return new SpecificDamage(this);
    }
    
    @Override
    public boolean hasNoEffect(){
        return ((getNShields() == 0) && (weapons.isEmpty()));
    }
    
    private int arrayContainsType(ArrayList<Weapon>w, WeaponType t){
        int contador = 0;
        for (Weapon weapon : w){
            if(weapon.getType() == t)
                return contador;
            else
                contador++;
        }
        
        return NOUSE;
    }
    
    @Override
    public Damage adjust (ArrayList<Weapon>w, ArrayList<ShieldBooster>s){
        int shmin, weapmin;
        //weapmin = Math.min(this.nWeapons, w.size());
        shmin = Math.min(getNShields(), s.size());
        
        ArrayList<WeaponType> auxwt1 = new ArrayList<>();
        ArrayList<WeaponType> auxwt2 = new ArrayList<>();
        ArrayList<Weapon> auxw = new ArrayList<>();
        
            for(int i=0; i < weapons.size(); i++){
                auxwt1.add(this.weapons.get(i));
            }
            
            for(int i=0; i < w.size(); i++){
                auxw.add(w.get(i));
            }
            
            Iterator<WeaponType> it = auxwt1.iterator();
            int index;
            
            while(it.hasNext()){     
               WeaponType type = it.next();
               index = arrayContainsType(auxw,type);
               if(index != -1){
                   auxw.remove(index);
                   auxwt2.add(type);
               }
               it.remove();
            }
            
            return new SpecificDamage(auxwt2,shmin);        
    }
    
    @Override
    public void discardWeapon(Weapon w){
        if((weapons != null) && (!weapons.isEmpty()))
            if(weapons.indexOf(w.getType()) != NOUSE)
                weapons.remove(weapons.indexOf(w.getType()));
    }
    
    @Override
    public SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
    }
    
    @Override
    public String toString(){
        String test;
        //CONTENT
        //String nSh = "DAMAGE: \n- NSHIELDS: " + nShields + " ";
        //String nW = "- NWEAPONS: " + nWeapons + "\n";
        
        String arrWT = "- WEAPONS: ";
        if(weapons == null || weapons.isEmpty()){
            arrWT += "Ninguno\n";
        }
        else{
            arrWT += weapons.toString() + "\n";
        }
        
        test = super.toString() + arrWT;
        
        return test;
    }
    
}
