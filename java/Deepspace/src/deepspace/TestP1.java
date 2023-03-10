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
public class TestP1 {
    
    public static void main(String[] args) {
        final int NUMERODICES = 100;
        
        // TODO code application logic here
        System.out.println("################# LOOT ##################");
        //Loot(int nsupplies, int nweapons, int nshields, int nhangars, int nmedals)
        Loot loot1 = new Loot(1,2,3,4,5);
        System.out.println(loot1.mostrar());
        Loot loot2 = new Loot(5,4,3,2,1);
        System.out.println(loot2.mostrar());
        Loot loot3 = new Loot(0,2,4,6,8);
        System.out.println(loot3.mostrar());
        System.out.println("########################################");
        
        System.out.println("\n\n############ SUPPLIES PACKAGE ############");
        //SuppliesPackage(float ammopower, float fuelunits, float shieldpower)
        SuppliesPackage supplies1 = new SuppliesPackage(1.0f, 1.0f, 1.0f);
        System.out.println(supplies1.mostrar());
        SuppliesPackage supplies2 = new SuppliesPackage(0.0f, 5.0f, 10.0f);
        System.out.println(supplies2.mostrar());
        SuppliesPackage supplies3 = new SuppliesPackage(supplies1);
        System.out.println(supplies3.mostrar());
        System.out.println("####################################################");
        
        
        System.out.println("\n\n################# SHIELDBOOSTER ################");
        //ShieldBooster(String name, float boost, int uses)
        ShieldBooster shieldbooster1 = new ShieldBooster ("ShieldBooster1", 5.0f, 2);
        System.out.println(shieldbooster1.mostrar());
        //vemos que funciona el metodo useit:
        /*float uso = shieldbooster1.useIt();
        System.out.println("devolucion de useit: " + uso + "\n");
        System.out.println(shieldbooster1.mostrar());
        uso = shieldbooster1.useIt();
        System.out.println("devolucion de useit: " + uso + "\n");
        System.out.println(shieldbooster1.mostrar());
        uso = shieldbooster1.useIt();
        System.out.println("devolucion de useit: " + uso + "\n");
        System.out.println(shieldbooster1.mostrar());*/
        
        ShieldBooster shieldbooster2 = new ShieldBooster ("ShieldBooster2", 3.0f, 3);
        System.out.println(shieldbooster2.mostrar());
        
        ShieldBooster shieldbooster3 = new ShieldBooster (shieldbooster1);  //constructor de copia
        System.out.println(shieldbooster3.mostrar());
        System.out.println("############################################");
        
        
        System.out.println("\n\n################ WEAPON #################");
        //Weapon (String name, WeaponType type, int uses)
        Weapon weapon1 = new Weapon("Weapon1", WeaponType.LASER, 3);
        System.out.println(weapon1.mostrar());
        
        Weapon weapon2 = new Weapon("Weapon2", WeaponType.MISSILE, 1);
        System.out.println(weapon2.mostrar());
        //vemos que funciona el metodo useit:
        float uso = weapon2.useIt();
        System.out.println("devolucion de useit: " + uso + "\n");
        System.out.println(weapon2.mostrar());
        uso = weapon2.useIt();
        System.out.println("devolucion de useit: " + uso);
        
        Weapon weapon3 = new Weapon(weapon1);
        System.out.println(weapon3.mostrar());
        System.out.println("##############################################");
        
        
        System.out.println("\n\n##################### DICE #################");
        //Dice dice1 = new Dice();
        //ArrayList<Weapon> arrayweapons2 = new ArrayList<>();
        Dice[] dices = new Dice[NUMERODICES];
        for(int i=0; i<100; i++){
            dices[i] = new Dice();
        }
        
        int result0 = 0;
        int result1 = 0;
        //Comprobacion metodo initWithNHangars:
        for(int i=0; i<NUMERODICES; i++){
            int result = dices[i].initWithNHangars();
            if (result == 0)
                result0++;
            else result1++;
        }  
        System.out.println("Comprobacion metodo initWithNHangars. Deberían salir sobre 25 result0 y 75 result1: ");
        System.out.println("    result0: " + result0 + "  result1: " + result1 + "\n ");
        
        //Comprobacion metodo initWithNWeapons:
        result1 = 0;
        int result2 = 0;
        int result3 = 0;
        for(int i=0; i<NUMERODICES; i++){
            int result = dices[i].initWithNWeapons();
            switch (result) {
                case 1:
                    result1++;
                    break;
                case 2:
                    result2++;
                    break;
                default:
                    result3++;
                    break;
            }
        }  
        System.out.println("Comprobacion metodo initWithNWeapons. Deberían salir sobre 33 result1, 33 result2 y 33 result3: ");
        System.out.println("    result1: " + result1 + "  result2: " + result2 + "  result3: "+ result3 + "\n ");
    
        //Comprobacion metodo initWithNShields:
        result1 = 0;
        result0 = 0;
        for(int i=0; i<NUMERODICES; i++){
            int result = dices[i].initWithNShields();
            if (result == 0)
                result0++;
            else result1++;
        }
        System.out.println("Comprobacion metodo initWithNShields. Deberían salir sobre 25 result0 y 75 result1: ");
        System.out.println("    result0: " + result0 + "  result1: " + result1 + "\n ");
    
        //Comprobacion metodo whoStarts:
        result0 = 0;
        result1 = 0;
        result2 = 0;
        result3 = 0;
        for(int i=0; i<NUMERODICES; i++){
            int result = dices[i].whoStarts(4);
            switch (result) {
                case 0:
                    result0++;
                    break;
                case 1:
                    result1++;
                    break;
                case 2:
                    result2++;
                    break;
                case 3:
                    result3++;
                    break;
            }
        }
        System.out.println("Comprobacion metodo whoStarts. Deberían salir sobre 25 result0, 25 result1, 25 result2 y 25 result3: ");
        System.out.println("    result0: " + result0 + "    result1: "+ result1 + "  result2: " + result2 + "  result3: "+ result3 + "\n ");
    
        
        //Comprobacion metodo firstShot:
        result0 = 0;
        result1 = 0;
        for(int i=0; i<NUMERODICES; i++){
            GameCharacter result = dices[i].firstShot();
            if (result == GameCharacter.SPACESTATION)
                result0++;
            else result1++;
        }
        System.out.println("Comprobacion metodo firstShot. Deberían salir sobre 50 result0 y 50 result1: ");
        System.out.println("    result0: " + result0 + "  result1: " + result1 + "\n ");
    
        
        //Comprobacion metodo spaceStationMoves:
        result0 = 0;
        result1 = 0;
        final float PROB = 0.5f;
        for(int i=0; i<NUMERODICES; i++){
            boolean result = dices[i].spaceStationMoves(PROB);
            if (result == true)
                result0++;
            else result1++;
        }
        System.out.println("Comprobacion metodo spaceStationMoves. Deberían salir sobre 50 result0 y 50 result1: ");
        System.out.println("    result0: " + result0 + "  result1: " + result1);
        System.out.println("######################################");
    }
}


