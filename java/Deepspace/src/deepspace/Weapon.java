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
public class Weapon implements CombatElement{   //quito el public?
    private String name;
    private WeaponType type;
    private int uses;
    
    Weapon (String na, WeaponType ty, int us){
        name = na;
        type = ty;
        uses = us;
    }
    
    Weapon (Weapon copia){
        this(copia.name, copia.type, copia.uses);
        /*name = copia.name;
        type = copia.type;
        uses = copia.uses;*/
    }
    
    public WeaponType getType(){
        return type;
    }
    
    @Override
    public int getUses(){
        return uses;
    }
    
    public float power(){
        return type.getPower();
    }
    
    @Override
    public float useIt(){
        if(uses > 0){
            uses--;
            return power();
        }else
            return 1.0f;
    }
    
    WeaponToUI getUIversion(){
        return new WeaponToUI(this);
    }
    
    //EXTRA: PARA VER FACILMENTE EL CONTENIDO DE UNA INSTANCIA DE WEAPON:
    @Override
    public String toString(){
        String result = "name: "  + this.name + "\n";
        result  = result + "    Type: " + getType() + "\n";
        result  = result + "    Uses: " + getUses() + "\n";
        result  = result + "    Power: " + power() + "\n";
        
        return result;
    }
}
