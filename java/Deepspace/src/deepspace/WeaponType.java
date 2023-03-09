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
public enum WeaponType {
    LASER(2.0f), MISSILE(3.0f), PLASMA(4.0f);
    
    private float power;    //final???
    
    WeaponType(float p){
        power = p;
    }
    
    float getPower(){
        return power;
    }
    
    //LASER(2.0f), MISSILE(3.0f), PLASMA(4.0f); //tiene que ir en la primera linea, si no da error
}

