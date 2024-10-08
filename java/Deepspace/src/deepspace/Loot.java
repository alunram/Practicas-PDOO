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
public class Loot {     //Se quita el public?. En los consultores se debe poner final?
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;
    private boolean getEfficient;
    private boolean spaceCity;
    
    Loot(int nsu, int nw, int nsh, int nh, int nm){
        nSupplies = nsu;
        nWeapons = nw;
        nShields = nsh;
        nHangars = nh;
        nMedals = nm;
        getEfficient = false;
        spaceCity = false;
    }
    
    Loot(int nsu,int nw,int nsh, int nh,int nm, boolean ge, boolean sc){
        nSupplies = nsu;
        nWeapons = nw;
        nShields = nsh;
        nHangars = nh;   
        nMedals = nm;
        getEfficient = ge;
        spaceCity = sc;
    }
    
    public final boolean getEfficient(){
        return getEfficient;
    }
    
    public final boolean spaceCity(){
        return spaceCity;
    }
    
    public int getNSupplies(){
        return nSupplies;
    }
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    public int getNShields(){
        return nShields;
    }
    
    public int getNHangars(){
        return nHangars;
    }
    
    public int getNMedals(){
        return nMedals;
    }
    
    LootToUI getUIversion(){
        return new LootToUI(this);
    }
    
    //EXTRA: PARA VER FACILMENTE EL CONTENIDO DE UNA INSTANCIA DE LOOT: (para la p2 lo pide, le cambio el nombre a toString)
    @Override
    public String toString(){
        String result = "Loot: ";
        result  = result + "NSupplies: " + getNSupplies() + "\n";
        result  = result + "    NWeapons: " + getNWeapons() + "\n";
        result  = result + "    NShields: " + getNShields() + "\n";
        result  = result + "    NHangars: " + getNHangars() + "\n";
        result  = result + "    NMedals: " + getNMedals() + "\n";
        
        return result;
    }
    
    
}
