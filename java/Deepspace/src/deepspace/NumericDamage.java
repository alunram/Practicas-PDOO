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
public class NumericDamage extends Damage{
    private int nWeapons;
    
    NumericDamage(int w, int s){
        super(s);
        nWeapons = w;
    }
    
    NumericDamage(NumericDamage danio){
        super(danio.getNShields());
        nWeapons = danio.nWeapons;
    }
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    @Override
    public NumericDamage copy(){
        return new NumericDamage(nWeapons, getNShields());
    }
    
    @Override
    public void discardWeapon(Weapon w){
        if(nWeapons > 0)
            nWeapons--;
    }
    
    @Override
    public boolean hasNoEffect(){
        return ((getNShields() == 0) && (nWeapons == 0));
    }
    
    @Override
    public Damage adjust (ArrayList<Weapon>w, ArrayList<ShieldBooster>s){
        int shmin, weapmin;
        weapmin = Math.min(this.nWeapons, w.size());
        shmin = Math.min(getNShields(), s.size());
               
        return new NumericDamage(weapmin,shmin);  
    }
    
    @Override
    public NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }
    
    @Override
    public String toString(){
        String test;
        //CONTENT
        String nW = "- NWEAPONS: " + nWeapons + "\n";
     
        test = super.toString() + nW;
        
        return test;
    }
        
}
