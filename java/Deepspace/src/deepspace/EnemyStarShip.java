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
public class EnemyStarShip {
    private float ammoPower;
    private String name;
    private float shieldPower;
    private Loot loot;
    private Damage damage;
    
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        name = n;
        ammoPower = a;
        shieldPower = s;
        loot = l;
        damage = d;
    }
    
    EnemyStarShip(EnemyStarShip e){
        name = e.getName();
        ammoPower = e.getAmmoPower();
        shieldPower = e.getShieldPower();
        loot = e.getLoot();
        damage = e.getDamage(); 
    }
    
    EnemyToUI getUIversion(){
        return new EnemyToUI(this);
    }
    
    public float fire(){
        return ammoPower;
    }
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public Damage getDamage(){
        return damage;
    }
    
    public Loot getLoot(){
        return loot;
    }
    
    public String getName(){
        return name;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    public float protection() {
        return shieldPower;
    }
    
    public ShotResult receiveShot (float shot) {
        if(shot > shieldPower)
            return ShotResult.DONOTRESIST;
        else
            return ShotResult.RESIST;
    }
    
    @Override
    public String toString(){
        String result = "por hacer";
        
        String n = "Nombre: " + this.name + "\n";
        String sp = "ShieldPower: " + this.shieldPower + "\n";
        String ap = "AmmoPower: " + this.ammoPower + "\n";
        String l = "Loot: " + loot.toString();
        String d = "Damage: " + damage.toString();
                
        result = n + sp + ap + l + d;
        
        return result;
    }
    
}
