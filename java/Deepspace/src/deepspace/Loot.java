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
    
    Loot(int nsu, int nw, int nsh, int nh, int nm){
        nSupplies = nsu;
        nWeapons = nw;
        nShields = nsh;
        nHangars = nh;
        nMedals = nm;
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
    
    //EXTRA: PARA VER FACILMENTE EL CONTENIDO DE UNA INSTANCIA DE LOOT:
    public String mostrar(){
        String result = "Loot: ";
        result  = result + "NSupplies: " + getNSupplies() + "\n";
        result  = result + "    NWeapons: " + getNWeapons() + "\n";
        result  = result + "    NShields: " + getNShields() + "\n";
        result  = result + "    NHangars: " + getNHangars() + "\n";
        result  = result + "    NMedals: " + getNMedals() + "\n";
        
        return result;
    }
}
