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
public class TestP2_3 {
    public static void main(String[] args) {
        //Supplies, loots, armas y escudos
        Loot loot1 = new Loot(1,2,3,4,5);
        Loot loot2 = new Loot(1,1,1,1,1);
        
        SuppliesPackage supplies1 = new SuppliesPackage(2.0f, 3.0f, 5.0f);
        SuppliesPackage supplies2 = new SuppliesPackage(4.0f, 5.0f, 6.0f);
        SuppliesPackage supplies3 = new SuppliesPackage(7.0f, 8.0f, 9.0f);
        
        Weapon weapon1 = new Weapon("Weapon1", WeaponType.LASER, 32);
        Weapon weapon2 = new Weapon("Weapon2", WeaponType.MISSILE, 5);
        Weapon weapon3 = new Weapon("Weapon3", WeaponType.PLASMA, 10);
        Weapon weapon4 = new Weapon("Weapon4", WeaponType.LASER, 15);
        Weapon weapon5 = new Weapon("Weapon5", WeaponType.MISSILE, 7);
        Weapon weapon6 = new Weapon("Weapon6", WeaponType.PLASMA, 2);
        
        ShieldBooster shieldbooster1 = new ShieldBooster ("ShieldBooster1", 3.0f, 15);
        ShieldBooster shieldbooster2 = new ShieldBooster ("ShieldBooster2", 2.5f, 50);
        ShieldBooster shieldbooster3 = new ShieldBooster ("ShieldBooster3", 1.5f, 3);
        
        //HANGAR
        System.out.println("\n\n################# HANGAR ################");
        Hangar hangar1 = new Hangar (2);
        System.out.println(hangar1.toString());
        
        hangar1.addWeapon(weapon1);
        hangar1.addWeapon(weapon2);
        hangar1.addWeapon(weapon1); //vemos que no se ha añadido
        System.out.println(hangar1.toString());
        
        Hangar hangar2 = new Hangar (5);
        hangar2.addWeapon(weapon1);
        hangar2.addWeapon(weapon2);
        hangar2.addShieldBooster(shieldbooster1);
        System.out.println(hangar2.toString());
        
        Hangar hangar3 = new Hangar(hangar2);
        System.out.println(hangar3.toString());
        hangar3.removeShieldBooster(0);
        hangar3.removeWeapon(0);
        System.out.println(hangar3.toString());
        hangar3.removeWeapon(0);
        hangar3.removeWeapon(0); //no hace nada
        hangar3.removeShieldBooster(0); //no hace nada
        System.out.println(hangar3.toString());
        
        //Damage
        //Arraylist: add(object) (devuelve boolean), clear() borra, isEmpty() dice si esta vacio o no,
        //remove(object) (devuelve boolean), get(indice), contains(object) dice si esta contenido en el arraylist, size()
        System.out.println("\n\n################# DAMAGE ################");
        ArrayList<ShieldBooster> arraysb = new ArrayList<>();
        ArrayList<ShieldBooster> arraysb2 = new ArrayList<>();
        ArrayList<Weapon> arrayw = new ArrayList<>();
        ArrayList<Weapon> arrayw2 = new ArrayList<>();
        ArrayList<Weapon> arrayw3 = new ArrayList<>(); //vacio
        ArrayList<WeaponType> arraywt = new ArrayList<>();
        ArrayList<WeaponType> arraywt2 = new ArrayList<>();
        ArrayList<WeaponType> arraywt3 = new ArrayList<>(); //vacio
        ArrayList<WeaponType> arraywt4 = new ArrayList<>();
        arraysb.add(shieldbooster1); arraysb.add(shieldbooster2); arraysb.add(shieldbooster3);
        arrayw.add(weapon1); arrayw.add(weapon2); arrayw.add(weapon3);
        arraywt.add(weapon1.getType()); arraywt.add(weapon2.getType()); arraywt.add(weapon3.getType());
        arrayw2.add(weapon1); arrayw2.add(weapon2); arrayw2.add(weapon3); arrayw2.add(weapon4); arrayw2.add(weapon5); arrayw2.add(weapon6);
        arraywt2.add(weapon1.getType()); arraywt2.add(weapon2.getType()); arraywt2.add(weapon3.getType()); arraywt2.add(weapon4.getType()); arraywt2.add(weapon5.getType()); arraywt2.add(weapon6.getType());
        arraywt4.add(weapon1.getType()); //WeaponType.LASER
        
       /* Damage damage1 = new Damage(3,5);
        Damage damage2 = new Damage(damage1);
        Damage damage3 = new Damage(arraywt2,10);
        Damage damage4 = new Damage(damage3);
        Damage damage5 = new Damage(0,0);
        Damage damage6 = new Damage(1,0);
        
        System.out.println(damage1.toString());
        System.out.println(damage2.toString());
        System.out.println(damage3.toString());
        System.out.println(damage4.toString());
        
        damage4.discardShieldBooster();
        System.out.println(damage4.toString());
        damage4.discardWeapon(weapon1);
        System.out.println(damage4.toString());
        damage4.discardWeapon(weapon1);
        System.out.println(damage4.toString());
        damage4.discardWeapon(weapon1); //no hara nada
        System.out.println(damage4.toString());
        
        if(damage4.hasNoEffect()){
            System.out.println("Damage4 no tiene efecto");
        }else System.out.println("Damage4 si tiene efecto");
        
        if(damage5.hasNoEffect()){
            System.out.println("Damage5 no tiene efecto");
        }else System.out.println("Damage5 si tiene efecto");
        
        //FALTA PROBAR EL ADJUST
        
        //enemy
        System.out.println("\n\n################# ENEMY ################");
        EnemyStarShip enemy1 = new EnemyStarShip("Enemy1", 1.0f, 1.0f, loot1, damage6);
        System.out.println(enemy1.toString());
        EnemyStarShip enemy2 = new EnemyStarShip(enemy1);
        System.out.println(enemy2.toString());
        
  
        //spacestation
        System.out.println("\n\n################# SPACESTATION ################");
        SpaceStation ss1 = new SpaceStation("SpaceStation1", supplies1);
        //System.out.println(ss1.toString());
        
        ss1.receiveHangar(hangar1);
        //System.out.println(ss1.toString());
        
        ss1.mountWeapon(0);
        //System.out.println(ss1.toString());
        
        ss1.discardHangar();
        //System.out.println(ss1.toString());
        
        ss1.receiveHangar(hangar2);
        //System.out.println(ss1.toString());
        
        System.out.println("INICIO: ");
        System.out.println(ss1.toString());
        
        //ss1.mountShieldBooster(0);
        //System.out.println(ss1.toString());
        
        System.out.println("DESCARTAR SHIELDBOOSTER EN HANGAR: ");
        ss1.discardShieldBoosterInHangar(0); 
        System.out.println(ss1.toString());
        
        System.out.println("DESCARTAR PRIMER WEAPON EN HANGAR: ");
        ss1.discardWeaponInHangar(0);
        System.out.println(ss1.toString());
        
        System.out.println("DESCARTAR WEAPON RESTANTE EN HANGAR: ");
        ss1.discardWeaponInHangar(0);
        System.out.println(ss1.toString());
        
        ss1.discardHangar();
        System.out.println(ss1.toString());
        
        float fire = ss1.fire();
        float protection = ss1.protection();
        float speed = ss1.getSpeed();
        
        System.out.println("fire: " + fire);
        System.out.println("protection: " + protection);
        System.out.println("speed: " + speed);
        
        ss1.setLoot(loot2);
        System.out.println(ss1.toString());
        
        fire = ss1.fire();
        protection = ss1.protection();
        speed = ss1.getSpeed();
        
        System.out.println("fire: " + fire);
        System.out.println("protection: " + protection);
        System.out.println("speed: " + speed);
        
        //gameuniverse
        System.out.println("\n\n################# GAME UNIVERSE ################");
        ArrayList<String> names = new ArrayList<>();
        names.add("uno"); names.add("dos");
        GameUniverse gu = new GameUniverse();
        gu.init(names);
        System.out.println(gu.toString());
        
        //adjust
        System.out.println("\n\n################# ADJUST ################");
        //voy a hacer los mismos casos que en la prueba de ruby, con los mismos damages:
        Damage dam1 = new Damage(3,5);
        Damage dam2 = new Damage(arraywt2, 10);
        Damage dam3 = new Damage(0,0);
        Damage dam4 = new Damage(1,0);
        Damage dam5 = new Damage(arraywt,5);
        Damage dam6 = new Damage(arraywt2,0);
        Damage dam7 = new Damage(7,0);
        Damage dam8 = new Damage(0,7);
        Damage dam9 = new Damage(arraywt3,5);
        Damage dam10 = new Damage(7,1);
        Damage dam11 = new Damage(arraywt2,1);
        Damage dam12 = new Damage(3,7);
        Damage dam13 = new Damage(arraywt,5);
        Damage dam14 = new Damage(arraywt3,0);
        Damage dam15 = new Damage(1,1);
        Damage dam16 = new Damage(arraywt4,1);
        Damage dam17 = new Damage(3,3);
        Damage dam18 = new Damage(arraywt,3);
        
        //caso 1 / normal, hay de todo bien
        System.out.println("caso 1 / normal, hay de todo bien");
        System.out.println(dam1.adjust(arrayw, arraysb)); //sale un damage de 3 y 3
        System.out.println(dam2.adjust(arrayw, arraysb)); //sale un damage de 3 sb y las 3 wt del array
        
        //caso 2, no hay sb
        System.out.println("caso 2, no hay sb ");
        System.out.println(dam7.adjust(arrayw, arraysb)); //#sale un damage de 0sb y 3 w
        System.out.println(dam6.adjust(arrayw, arraysb)); //#sale un damage de 0sb y las 3 armas
        
        //caso 3, no hay weapons
        System.out.println("caso 3, no hay weapons ");
        System.out.println(dam8.adjust(arrayw, arraysb)); //#sale un damage de 0sb y 3 w
        System.out.println(dam9.adjust(arrayw, arraysb)); //sale un damage de 0sb y las 3 armas
        
        //caso 4, no hay suficientes sb pero si hay alguno
        System.out.println("caso 4, no hay suficientes sb pero si hay alguno ");
        System.out.println(dam10.adjust(arrayw, arraysb)); //sale un damage de 1sb y 3 w
        System.out.println(dam11.adjust(arrayw, arraysb)); //sale un damage de 1sb y las 3w
        
        //caso 5, no hay suficientes weapons pero si hay alguno
        System.out.println("caso 5, no hay suficientes weapons pero si hay alguno ");
        System.out.println(dam12.adjust(arrayw2, arraysb)); //#sale un damage de 3 y 3
        System.out.println(dam13.adjust(arrayw2, arraysb)); //#sale un damage de 3 y 3
        
        //caso 6, no hay de nada (0)
        System.out.println("caso 6, no hay de nada (0) ");
        System.out.println(dam3.adjust(arrayw, arraysb)); //sale un damage vacio
        System.out.println(dam14.adjust(arrayw, arraysb)); //sale un damage vacio
        
        //caso 7, no hay suficientes de ninguno de los dos, pero no es vacio
        System.out.println("caso 7, no hay suficientes de ninguno de los dos, pero no es vacio ");
        System.out.println(dam15.adjust(arrayw, arraysb)); //sale un damage de 1sb y 1w
        System.out.println(dam16.adjust(arrayw, arraysb)); //sale un damage de 1sb y un weapon de tipo laser
        
        //caso 8, los array por parametros estan vacios
        System.out.println("caso 8, los array por parametros estan vacios ");
        System.out.println(dam1.adjust(arrayw3, arraysb2)); //sale un damage vacio
        System.out.println(dam2.adjust(arrayw3, arraysb2)); //sale un damage vacio
         
        //caso9, se tiene justo lo que pide
        System.out.println("caso9, se tiene justo lo que pide ");
        System.out.println(dam17.adjust(arrayw, arraysb)); //sale un damage de 3sb y 3w
        System.out.println(dam18.adjust(arrayw, arraysb)); //sale un damage de 3sb y 3w
    */}
}
