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
public abstract class Damage {
    //static private final int NOUSE = -1;
    private int nShields;
    //private int nWeapons;
    //private ArrayList<WeaponType> weapons = new ArrayList<>();
    
    /*Damage(int w, int s){
        nShields = s;
        nWeapons = w;
        weapons = null;
        //weapons = new ArrayList<>();
    }
    
    Damage(ArrayList<WeaponType>wl, int s){
        nShields = s;
        nWeapons = NOUSE;
        for(WeaponType w : wl){
            weapons.add(w);
        }
    }
    
    Damage (Damage d){
        this(((d.weapons==null)? d.getWeapons(): d.nWeapons), d.nShields); //sigue dando error de todas formas
        /*ESTO NO DEJA PORQUE EL THIS TIENE QUE IR EN LA PRIMERA LINEA. SI NO QUEREMOS REPETIR CODIGO, SE TENDRIA QUE HACER COMO ESTA ARRIBA
        if(d.weapons == null){
            this(d.nWeapons, d.nShields);
        }else{
            this(d.weapons, d.nShields); //tienes que pasar weapontypes! no weapons
        }   
    }*/
    
    /*Damage(int w, int s){
        constructorAuxiliar(w,s,null);
    }
    
    Damage(ArrayList<WeaponType>wl, int s){
        constructorAuxiliar(NOUSE,s, wl);
    }
    
    Damage (Damage d){
        if(d.weapons==null){
            constructorAuxiliar(d.nWeapons,d.nShields,null);
        }else{
            constructorAuxiliar(NOUSE,d.nShields, d.weapons);
        }
    }
    
    //metodo auxiliar para poder hacer el constructor de copia sin repetir codigo. el this solo se puede poner en la primera linea, lo que dificulta la reutilizacion
    private void constructorAuxiliar(int w, int s, ArrayList<WeaponType>wl){
        if(wl==null){
            nShields = s;
            nWeapons = w;
            weapons = null;
        }else{
            nShields = s;
            nWeapons = NOUSE;
            for(WeaponType wt : wl){
                weapons.add(wt);
            }
        }
    }*/
    
    Damage(int s){
        nShields = s;
    }
    
    public int getNShields(){
        return nShields;
    }
    
    public abstract Damage copy();
    
    public abstract DamageToUI getUIversion();
        
    public abstract Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    public abstract void discardWeapon(Weapon w);
    
    public void discardShieldBooster(){
        if(nShields > 0)
            nShields = nShields-1;
    }
    
    public abstract boolean hasNoEffect();
    
    /*DamageToUI getUIversion(){
        return new DamageToUI(this);
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
    
    public Damage adjust (ArrayList<Weapon>w, ArrayList<ShieldBooster>s){
        int shmin, weapmin;
        weapmin = Math.min(this.nWeapons, w.size());
        shmin = Math.min(this.nShields, s.size());
        
        ArrayList<WeaponType> auxwt1 = new ArrayList<>();
        ArrayList<WeaponType> auxwt2 = new ArrayList<>();
        ArrayList<Weapon> auxw = new ArrayList<>();
        
        if ((weapons != null) && (!weapons.isEmpty())){
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
            
            return new Damage(auxwt2,shmin);
        }
        
        return new Damage(weapmin,shmin);
        
    }
    
    public void discardWeapon(Weapon w){
        if(nWeapons > 0){
            nWeapons--;
        }else if((weapons != null) && (!weapons.isEmpty()))
            if(weapons.indexOf(w.getType()) != -1)
                weapons.remove(weapons.indexOf(w.getType()));
    }
    
    public void discardShieldBooster(){
        if(nShields > 0)
            nShields--;
    }
    
    public boolean hasNoEffect(){
        if(weapons == null)
            return ((nShields == 0) && (nWeapons == 0));
        else return ((nShields == 0) && (weapons.isEmpty()));
    }
    
    
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    public ArrayList<WeaponType> getWeapons(){
        return weapons;
    }*/
    
    @Override
    public String toString(){
        String test;
        //CONTENT
        String nSh = "DAMAGE: \n- NSHIELDS: " + nShields + " ";
        /*String nW = "- NWEAPONS: " + nWeapons + "\n";
        
        String arrWT = "- WEAPONS: ";
        if(weapons == null || weapons.isEmpty()){
            arrWT += "Ninguno\n";
        }
        else{
            arrWT += weapons.toString() + "\n";
        }
        
        test = nSh + nW + arrWT;
        
        return test;*/
        return nSh;
    }
}
