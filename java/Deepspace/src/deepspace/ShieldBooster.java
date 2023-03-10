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
public class ShieldBooster { //quito el public?
    String name;
    float boost;
    int uses;
    
    ShieldBooster(String n, float b, int u){
        name = n;
        boost = b;
        uses = u;
    }
    
    ShieldBooster(ShieldBooster copia){
        this(copia.name, copia.boost, copia.uses);
        /*name = copia.name;
        boost = copia.boost;    
        uses = copia.uses;*/
    }
    
    public float getBoost(){
        return boost;
    }
    
    public int getUses(){
        return uses;
    }
    
    public float useIt(){
        if(uses > 0){
            //uses = uses - 1;
            uses--;
            return boost;
        }else
            return 1.0f;
    }
    
    //EXTRA: PARA VER FACILMENTE EL CONTENIDO DE UNA INSTANCIA DE SHIELDBOOSTER:
    public String mostrar(){
        String result = "name: "  + this.name + "\n";
        result  = result + "    Boost: " + getBoost() + "\n";
        result  = result + "    Uses: " + getUses() + "\n";
        
        return result;
    }
}
