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
public class Weapon {   //quito el public?
    private String name;
    private WeaponType type;
    private int uses;
    
    Weapon (String na, WeaponType ty, int us){
        name = na;
        type = ty;
        uses = us;
    }
    
    Weapon (Weapon copia){
        name = copia.name;
        type = copia.type;
        uses = copia.uses;
    }
    
    public WeaponType getType(){
        return type;
    }
    
    public int getUses(){
        return uses;
    }
    
    public float power(){
        return type.getPower();
    }
    
    public float useIt(){
        if(uses > 0){
            uses--;
            return power();
        }else
            return 1.0f;
    }
}
