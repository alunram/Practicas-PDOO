/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.Random;

/**
 *
 * @author alvaro
 */
public class Dice {
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    private final float EXTRAEFFICIENCYPROB;
    private Random generator;
    
    Dice(){
        NHANGARSPROB = 0.25f;
        NSHIELDSPROB = 0.25f;
        NWEAPONSPROB = 0.33f;
        FIRSTSHOTPROB = 0.5f;
        EXTRAEFFICIENCYPROB = 0.8f;
        generator = new Random();
    }
    
    public int initWithNHangars(){
        if(generator.nextFloat() < NHANGARSPROB)
            return 0;
        else
            return 1;
    }
    
    public int initWithNWeapons(){
        float floatgenerado = generator.nextFloat();
        if(floatgenerado < NWEAPONSPROB)
            return 1;
        else if (floatgenerado < 2*NWEAPONSPROB)
                return 2;
            else return 3;
    }
    
    public boolean extraEfficiency(){
        return (generator.nextFloat() < EXTRAEFFICIENCYPROB);
    }
    
    public int initWithNShields(){
        if(generator.nextFloat() < NSHIELDSPROB)
            return 0;
        else
            return 1;
    }
    
    public int whoStarts(int nPlayers){
        return generator.nextInt(nPlayers);
    }
    
    public GameCharacter firstShot(){
        if(generator.nextFloat() < FIRSTSHOTPROB){
            return GameCharacter.SPACESTATION;
        }else return GameCharacter.ENEMYSTARSHIP;
    }
    
    public boolean spaceStationMoves(float speed){  //se asume que speed esta entre 0 y 1
        if(generator.nextFloat() < speed)
            return true;
        else return false;
    }
}
