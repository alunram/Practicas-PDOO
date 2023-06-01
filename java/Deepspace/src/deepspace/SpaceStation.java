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
public class SpaceStation implements SpaceFighter{
    private static final float MAXFUEL = 100f;
    private static final float SHIELDLOSSPERUNITSHOT = 0.1f; 
    
    private float ammoPower;
    private float fuelUnits;
    private String name;
    private int nMedals;
    private float shieldPower;
    private Damage pendingDamage;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList<>();
    private Hangar hangar;
    
    SpaceStation(String n, SuppliesPackage supplies){
        name = n;
        ammoPower = supplies.getAmmoPower();
        fuelUnits = supplies.getFuelUnits();
        shieldPower = supplies.getShieldPower();
        nMedals = 0;
        pendingDamage = null;
        /*weapons = null;
        shieldBoosters = null;      //si pongo estos nulls peta al iniciar
        hangar = null;*/
    }
    
    SpaceStation(SpaceStation station){
        this(station.getName(), new SuppliesPackage(station.getAmmoPower(), station.getFuelUnits(), station.getShieldPower()));
        pendingDamage = station.pendingDamage;
        nMedals = station.nMedals;
        
        station.weapons.forEach(w -> {
            weapons.add(w);
        });
        station.shieldBoosters.forEach(s -> {
            shieldBoosters.add(s);
        });
        hangar = station.hangar;
    }
    
    private void assignFuelValue(float f){
        if (f < MAXFUEL) {
            this.fuelUnits = f;
        } else {
            this.fuelUnits = MAXFUEL;
        }
    }
    
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoEffect()){
           pendingDamage = null;             
        }
    }
    
    public void cleanUpMountedItems(){
        ArrayList<Weapon> auxweapons = new ArrayList<>();
        ArrayList<ShieldBooster> auxshield = new ArrayList<>();
        for(Weapon w: weapons){
            if (w.getUses() == 0)
                auxweapons.add(w);
        }
        for(Weapon w: auxweapons){
            weapons.remove(w);
        }
        
        for(ShieldBooster s: shieldBoosters){
            if(s.getUses() == 0)
                auxshield.add(s);
        }
        for(ShieldBooster s: auxshield){
            shieldBoosters.remove(s);
        }
    }
    
    public void discardHangar(){
         hangar = null;
    }
    
    public void discardShieldBooster(int i){
        int size = shieldBoosters.size();
        
        if((i>=0) && (i<size)){
            shieldBoosters.remove(i);
            if(pendingDamage != null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }
    
    public void discardShieldBoosterInHangar(int i){
        if (hangar != null && i>=0 && hangar.getShieldBoosters().size() > i){
            hangar.removeShieldBooster(i);
        }
    }
    
    public void discardWeapon(int i){
        int size = weapons.size();
        
        if((i>=0) && (i<size)){
            Weapon w = weapons.remove(i);
            if(pendingDamage != null){
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
        }
    }
    
    public void discardWeaponInHangar(int i){
        if (hangar != null && i>=0 && hangar.getWeapons().size() > i){
            hangar.removeWeapon(i);
        }
    }
    
    @Override
    public float fire(){
        int size = weapons.size();
        float factor = 1;
        Weapon w;
        
        for(int i=0; i<size; i++){
            w = weapons.get(i);
            factor *= w.useIt();
        }
        
        return ammoPower*factor;     
    }
    
    public float getAmmoPower() {
        return this.ammoPower;
    }
    
    public float getFuelUnits() {
        return this.fuelUnits;
    }
    
    public Hangar getHangar() {
        return hangar;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getNMedals() {
        return nMedals;
    }
    
    public Damage getPendingDamage() {
        return pendingDamage;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters() {
        return shieldBoosters;
    }
    
    public float getShieldPower() {
        return shieldPower;
    }
    
    public float getSpeed() {
        float result;
        result = fuelUnits/MAXFUEL;
        
        return result;
    }
    
    public SpaceStationToUI getUIversion() {
        return new SpaceStationToUI(this);
    }
    
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    
    public void mountShieldBooster(int i){
        if(hangar != null){
            if((0<=i) && (i<hangar.getShieldBoosters().size())){
                ShieldBooster shieldBooster = hangar.removeShieldBooster(i);
                if(shieldBooster != null)
                    shieldBoosters.add(shieldBooster);
            }
        }
    }
    
    public void mountWeapon(int i){
        if(hangar != null){
            if((0<=i) && (i<hangar.getWeapons().size())){
                Weapon weapon = hangar.removeWeapon(i);
                if(weapon != null)
                    weapons.add(weapon);
            }
        }
    }
    
    public void move(){
        fuelUnits = fuelUnits - getSpeed();
        
        if(fuelUnits<0){
            fuelUnits = 0;
        }
    }
    
    @Override
    public float protection(){
        int size = shieldBoosters.size();
        float factor = 1;
        ShieldBooster s;
        
        for(int i=0; i<size;i++){
            s = shieldBoosters.get(i);
            factor *= s.useIt();
        }
        
        return factor*shieldPower;
    }
    
    public void receiveHangar(Hangar h){
        if(hangar == null){
            hangar = h; 
        }
    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        if(hangar != null){
            return hangar.addShieldBooster(s);
        }else return false;
    }
    
    @Override
    public ShotResult receiveShot(float shot){
        float myProtection = protection();
        
        if (myProtection >= shot){
            shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
            shieldPower = Math.max(0.0f,shieldPower);
            return ShotResult.RESIST;
        }else{
            shieldPower = 0.0f;
            return ShotResult.DONOTRESIST;
        } 
    }
    
    public void receiveSupplies(SuppliesPackage s){
        ammoPower = ammoPower + s.getAmmoPower();
        fuelUnits = fuelUnits + s.getFuelUnits();
        if(fuelUnits > MAXFUEL)
            fuelUnits = MAXFUEL;
        shieldPower = shieldPower + s.getShieldPower();
    }
    
    public boolean receiveWeapon(Weapon w){
        if(hangar != null){
            return hangar.addWeapon(w);
        }else return false;
    }
    
    public Transformation setLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();
        int h = loot.getNHangars();
        
        if(h>0){
            Hangar hangar = dealer.nextHangar();
            receiveHangar(hangar);
        }
        
        int elements=loot.getNSupplies();
        for(int i=0; i<elements; i++){
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }
        
        elements = loot.getNWeapons();
        for(int i=0; i<elements; i++){
            Weapon weap = dealer.nextWeapon();
            receiveWeapon(weap);
        }
        
        elements = loot.getNShields();
        for(int i=0;i<elements; i++){
            ShieldBooster sh = dealer.nextShieldBooster();
            receiveShieldBooster(sh);
        }
        
        int medals = loot.getNMedals();
        
        nMedals += medals;
        
        if(loot.getEfficient()){
            return Transformation.GETEFFICIENT;
        }else{
            if(loot.spaceCity()){
                return Transformation.SPACECITY;
            } else return Transformation.NOTRANSFORM;
        }
    }
    
    public void setPendingDamage(Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
        
        cleanPendingDamage();
    }
    
    public boolean validState() {
        if (pendingDamage == null){
            return true;
        }else return (pendingDamage.hasNoEffect());
    }
     
    @Override
    public String toString(){
        String n = "Nombre: " + name + "\n";
        String p = "AmmoPower: " + ammoPower + "\n";
        String f = "FuelUnits: " + fuelUnits + "\n";    
        String sp = "ShieldPower: " + shieldPower + "\n";
        String m = "NºMedals: " + nMedals + "\n";
        
        String w = "Weapons: ";
        for(Weapon weap : weapons){
            w += weap.toString();
        }
        w += "\n";
        
        String sb = "ShieldBoosters: ";
        for(ShieldBooster shieldb : shieldBoosters){
                sb += shieldb.toString();
        }
        sb += "\n";
        String last = n + p + f + sp + m + w + sb;
        
        String h = "Hangar: ";
        if (hangar != null){
            h += hangar.toString() + "\n";  
        }else h += "null \n";
        last += h;
        
        String pd = "PendingDamage: ";
        if(pendingDamage != null){
             pd += pendingDamage.toString() + "\n"; 
             
        }else pd += "null \n";
        
        last += pd;
        
        return last;
    }
}
