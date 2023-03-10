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
public class SuppliesPackage {  //Se quita el public?
    private float ammoPower;
    private float fuelUnits;
    private float shieldPower;
    
    SuppliesPackage(float ap, float fu, float sp){
        ammoPower = ap;
        fuelUnits = fu;
        shieldPower = sp;
    }
    
    SuppliesPackage(SuppliesPackage copia){
        this(copia.ammoPower, copia.fuelUnits, copia.shieldPower);
        /*ammoPower = copia.ammoPower;
        fuelUnits = copia.fuelUnits;
        shieldPower = copia.shieldPower;*/
    }
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public float getFuelUnits(){
        return fuelUnits;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    //EXTRA: PARA VER FACILMENTE EL CONTENIDO DE UNA INSTANCIA DE LOOT:
    public String mostrar(){
        String result = "SuppliesPackage: ";
        result  = result + "    AmmoPower: " + getAmmoPower() + "\n";
        result  = result + "    FuelUnits: " + getFuelUnits() + "\n";
        result  = result + "    ShieldPower: " + getShieldPower() + "\n";
        
        return result;
    }
}
