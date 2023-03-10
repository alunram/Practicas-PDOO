#encoding:utf-8

require_relative 'WeaponType'
require_relative 'Loot'
require_relative 'SuppliesPackage'
require_relative 'ShieldBooster'
require_relative 'Weapon'
require_relative 'Dice'
require_relative 'GameCharacter'

#probar si quitando el nombre de la clase los constructores de copia siguen funcionando

module Deepspace
    puts "######## LOOT ######### \n"
    pruebaloot = Deepspace::Loot.new(1,2,3,4,5)
    puts pruebaloot.inspect
    puts "########################## \n\n"


    puts "######## SUPPLIES PACKAGE ######### \n"
    pruebasupplies = Deepspace::SuppliesPackage.new(1.0, 1.0, 1.0)
    puts pruebasupplies.inspect

    pruebasupplies2 = Deepspace::SuppliesPackage.newCopy(pruebasupplies)
    puts pruebasupplies2.inspect
    puts "########################## \n\n"


    puts "######## SHIELD BOOSTER ######### \n"
    pruebashieldbooster = Deepspace::ShieldBooster.new("PRUEBA", 5.0, 3)
    puts pruebashieldbooster.inspect

    pruebashieldbooster2 = Deepspace::ShieldBooster.newCopy(pruebashieldbooster)
    puts pruebashieldbooster2.inspect

    puts pruebashieldbooster.useIt
    puts pruebashieldbooster.inspect
    puts "########################## \n\n"


    puts "######## WEAPON ######### \n"
    pruebaweapon = Deepspace::Weapon.new("weaponprueba", Deepspace::WeaponType::LASER, 6.0)
    puts pruebaweapon.inspect
    puts "\n"

    puts "veo que es el mismo tipo:"
    puts pruebaweapon.type
    puts Deepspace::WeaponType::LASER
    puts "\n"

    pruebaweapon2 = Deepspace::Weapon.newCopy(pruebaweapon)
    puts pruebaweapon2.inspect
    puts "\n"

    puts "veo que al final sale 1 de power, cuando se acaban los usos"
    for i in 0..6 do
        puts pruebaweapon.useIt
        puts pruebaweapon.inspect
    end
    puts "########################## \n\n"


    puts "######## DICE ######### \n"
    pruebadice = Dice.new
    puts pruebadice.inspect

    puts "initWithNHangars: "
    puts pruebadice.initWithNHangars

    puts "initWithNWeapons: "
    puts pruebadice.initWithNWeapons

    puts "initWithNShields: "
    puts pruebadice.initWithNShields

    puts "whoStarts (5): "
    puts pruebadice.whoStarts(5)

    puts "firstShot:"
    puts pruebadice.firstShot
    
    puts "spaceStationMoves:"
    puts pruebadice.spaceStationMoves(0.3)
    puts "########################## \n\n"
end
    