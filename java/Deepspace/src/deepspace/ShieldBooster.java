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
    
    ShieldBooster(String n, int b, int u){
        name = n;
        boost = b;
        uses = u;
    }
    
    ShieldBooster(ShieldBooster copia){
        name = copia.name;
        boost = copia.boost;    //seria mas correcto usar los consultores?
        uses = copia.uses;
    }
    
    public float getBoost(){
        return boost;
    }
    
    public int getUses(){
        return uses;
    }
    
    public float useIt(){
        if(uses > 0){
            uses = uses--;
            return boost;
        }else
            return 1.0f;
    }
}
