#encoding:utf-8

require_relative 'Loot'
require_relative 'SuppliesPackage'
require_relative 'ShieldBooster'
require_relative 'Weapon'
require_relative 'WeaponType'
require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'ShotResult'

module Deepspace
    class TestP1
        @@NUMERODICES = 100

        def main
            puts "######################## WEAPONTYPE ######################## \n"
            #dos formas de mostrarlos por pantalla:
            puts Deepspace::WeaponType::LASER.to_s 
            puts Deepspace::WeaponType::LASER
            puts Deepspace::WeaponType::MISSILE
            puts Deepspace::WeaponType::PLASMA
            puts "############################################################## \n\n"

            
            puts "######################## GAMECHARACTER ######################## \n"
            puts Deepspace::GameCharacter::ENEMYSTARSHIP
            puts Deepspace::GameCharacter::SPACESTATION
            puts "############################################################## \n\n"


            puts "######################## SHOTRESULT ######################## \n"
            puts Deepspace::ShotResult::DONOTRESIST
            puts Deepspace::ShotResult::RESIST
            puts "############################################################## \n\n"


            puts "######################## LOOT ######################## \n"
            pruebaloot1 = Deepspace::Loot.new(1,2,3,4,5)
            puts pruebaloot1.to_s   #dos formas
            puts pruebaloot1.inspect
            pruebaloot2 = Deepspace::Loot.new(5,4,3,2,1)
            puts pruebaloot2.inspect
            pruebaloot3 = Deepspace::Loot.new(0,2,4,6,8)
            puts pruebaloot3.inspect
            puts "to_s:"
            puts pruebaloot1.to_s
            puts "############################################################## \n\n"
        

            
            puts "######################## SUPPLIES PACKAGE ######################## \n"
            pruebasupplies = Deepspace::SuppliesPackage.new(1.0, 1.0, 1.0)
            puts pruebasupplies.inspect
            puts "to_s:"    #se puede poner con inspect o con to_s
            puts pruebasupplies.to_s
            pruebasupplies2 = Deepspace::SuppliesPackage.new(0.0, 5.0, 10.0)
            puts pruebasupplies2.inspect
            pruebasupplies3 = Deepspace::SuppliesPackage.newCopy(pruebasupplies)
            puts pruebasupplies3.inspect
            puts "############################################################## \n\n"



            puts "######################## SHIELDBOOSTER ######################## \n"
            pruebashieldbooster1 = Deepspace::ShieldBooster.new("ShieldBooster1", 5.0, 2)
            puts pruebashieldbooster1.inspect
            puts "to_s:"
            puts pruebashieldbooster1.to_s
            puts pruebashieldbooster1.useIt
            puts pruebashieldbooster1.to_s
            puts pruebashieldbooster1.useIt
            puts pruebashieldbooster1.to_s
            puts pruebashieldbooster1.useIt #al final es 1, ya que tiene 0 usos

            pruebashieldbooster2 = Deepspace::ShieldBooster.new("ShieldBooster2", 3.0, 3)
            puts pruebashieldbooster2.to_s
            pruebashieldbooster3 = Deepspace::ShieldBooster.newCopy(pruebashieldbooster1)
            puts pruebashieldbooster3.to_s
            puts "############################################################## \n\n"
        

            puts "######################## WEAPON ######################## \n"
            pruebaweapon1 = Deepspace::Weapon.new("weapon1", Deepspace::WeaponType::LASER, 3)
            puts pruebaweapon1.inspect
            puts pruebaweapon1.to_s
            puts "\n"

            puts "veo que es el tipo laser:"
            puts pruebaweapon1.type
            puts Deepspace::WeaponType::LASER
            puts "\n"

            pruebaweapon2 = Deepspace::Weapon.new("weapon2", Deepspace::WeaponType::MISSILE, 1)
            puts pruebaweapon2.to_s
            puts "\n"

            puts "veo que es el tipo missile:"
            puts pruebaweapon2.type
            puts Deepspace::WeaponType::MISSILE
            puts "\n"

            pruebaweapon3 = Deepspace::Weapon.newCopy(pruebaweapon1)
            puts pruebaweapon3.to_s
            puts "\n"

            puts "veo que al final sale 1 de power, cuando se acaban los usos"
            for i in 0..1 do
                puts pruebaweapon2.useIt
                #puts pruebaweapon2.inspect
            end
            puts "############################################################## \n\n"



            puts "######################## DICE ######################## \n"
            vectordices = []
            for i in 0..@@NUMERODICES do
                vectordices[i]= Deepspace::Dice.new()
            end

            result0 = 0;
            result1 = 0;
            #Comprobacion metodo initWithNHangars:
            for i in 0..@@NUMERODICES do
                result = vectordices[i].initWithNHangars
                if result==0
                    result0 = result0 + 1
                else result1 = result1 + 1
                end
            end
            puts "Comprobacion metodo initWithNHangars. Deberían salir sobre 25 result0 y 75 result1: \n"
            puts "      result0:  #{result0}        result1: #{result1} \n"

            #Comprobacion metodo initWithNWeapons:
            result1 = 0;
            result2 = 0;
            result3 = 0;
            for i in 0..@@NUMERODICES do
                result = vectordices[i].initWithNWeapons
                if result==1
                    result1 = result1+ 1
                    elsif result==2 
                        result2 = result2 + 1
                        else result3 = result3 + 1
                end
            end
            puts "Comprobacion metodo initWithNWeapons. Deberían salir sobre 33 result1, 33 result2 y 33 result3: \n"
            puts "      result1:  #{result1}        result2: #{result2}         result3: #{result3} \n"

            result0 = 0;
            result1 = 0;
            #Comprobacion metodo initWithNShields:
            for i in 0..@@NUMERODICES do
                result = vectordices[i].initWithNShields
                if result==0
                    result0 = result0 + 1
                else result1 = result1 + 1
                end
            end
            puts "Comprobacion metodo initWithNShields. Deberían salir sobre 25 result0 y 75 result1: \n"
            puts "      result0:  #{result0}        result1: #{result1} \n"

            #Comprobacion metodo whoStarts:
            result0 = 0
            result1 = 0
            result2 = 0
            result3 = 0
            for i in 0..@@NUMERODICES do
                result = vectordices[i].whoStarts(4)
                case result
                when 0
                    result0 = result0 + 1
                when 1
                    result1 = result1 + 1
                when 2
                    result2 = result2 + 1
                when 3
                    result3 = result3 + 1         
                end
            end
            puts "Comprobacion metodo whoStarts. Deberían salir sobre 25 result0, 25 result1, 25 result2 y 25 result3: \n"
            puts "      result0:  #{result0}        result1: #{result1}         result2: #{result2}         result3: #{result3} \n"

            result0 = 0;
            result1 = 0;
            #Comprobacion metodo firstShot:
            for i in 0..@@NUMERODICES do
                result = vectordices[i].firstShot
                if result==Deepspace::GameCharacter::SPACESTATION
                    result0 = result0 + 1
                else result1 = result1 + 1
                end
            end
            puts "Comprobacion metodo firstShot. Deberían salir sobre 50 result0 y 50 result1: \n"
            puts "      result0:  #{result0}        result1: #{result1} \n"

            result0 = 0;
            result1 = 0;
            #Comprobacion metodo spaceStationMoves:
            for i in 0..@@NUMERODICES do
                result = vectordices[i].spaceStationMoves(0.5)
                if result==true
                    result0 = result0 + 1
                else result1 = result1 + 1
                end
            end
            puts "Comprobacion metodo spaceStationMoves. Deberían salir sobre 50 result0 y 50 result1: \n"
            puts "      result0:  #{result0}        result1: #{result1} \n"
            puts "############################################################## \n\n"

        end
    end

    testpractica1 = TestP1.new()
    puts testpractica1.main

end