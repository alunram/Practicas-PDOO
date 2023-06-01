#encoding:utf-8

require_relative 'Weapon'
require_relative 'Loot'
require_relative 'WeaponType'
require_relative 'ShieldBooster'
require_relative 'Damage'
require_relative 'Hangar'
require_relative 'EnemyStarShip'
require_relative 'SuppliesPackage'
require_relative 'SpaceStation'
require_relative 'GameUniverse'
require_relative 'SpecificDamage'
require_relative 'NumericDamage'

module Deepspace
    class TestP2
        @@NUM_ITERACCIONES = 5
        def main
            #SUPPLIES, LOOT, ARMAS Y ESCUDOS
            #si fuese fuera del modulo deepspace habria que poner Deepspace:: (como esta puesto en testp1)
            l1 = Loot.new(1, 2, 3, 4, 5)
            l2 = Loot.new(1, 1, 1, 1, 1)
            su1 = SuppliesPackage.new(2,3,5)
            su2 = SuppliesPackage.new(4,5,6)
            su3 = SuppliesPackage.new(7,8,9)
            w1 = Weapon.new("W1", WeaponType::LASER, 32)
            w2 = Weapon.new("W2", WeaponType::MISSILE, 5)
            w3 = Weapon.new("W3", WeaponType::PLASMA, 10)
            w4 = Weapon.new("W4", WeaponType::LASER, 15)
            w5 = Weapon.new("W5", WeaponType::MISSILE, 7)
            w6 = Weapon.new("W6", WeaponType::PLASMA, 2)
            sb1 = ShieldBooster.new("SB1", 3.0, 15)
            sb2 = ShieldBooster.new("SB2", 2.5, 50)
            sb3 = ShieldBooster.new("SB3", 1.5, 3)

            puts "#supplies1####################### HANGAR ######################## \n"
            #pruebahangar1
            h1 = Hangar.new(2)
            puts "Hangar1: " +  h1.to_s

            h1.addWeapon(w1)
            h1.addWeapon(w2)
            h1.addWeapon(w1) #vemos que no se ha añadido
            puts "Hangar1: " +  h1.to_s

            h2 = Hangar.new(5)
            h2.addWeapon(w1)
            h2.addWeapon(w2)
            h2.addShieldBooster(sb1)
            puts "Hangar2: " +  h2.to_s

            h3 = Hangar.newCopy(h2)
            puts "Hangar3: " +  h3.to_s
            h3.removeShieldBooster(0)
            h3.removeWeapon(0)
            puts "Hangar3: " +  h3.to_s
            h3.removeShieldBooster(0) #no hace nada
            h3.removeWeapon(0)
            h3.removeWeapon(0) #no hace nada
            puts "Hangar3: " +  h3.to_s

            puts "######################## DAMAGE ######################## \n"
            arraySB = [sb1,sb2,sb3]
            arrayW = [w1,w2,w3]
            arrayWT = [w1.type,w2.type,w3.type]
            arrayW2 = [w1,w2,w3,w4,w5,w6]
            arrayWT2 = [w1.type,w2.type,w5.type,w3.type,w4.type,w6.type]
            dam1 = NumericDamage.new(3,5)
            dam2 = dam1.copy()
            dam3 = SpecificDamage.new(arrayWT2,10)
            dam4 = dam3.copy()
            dam5 = NumericDamage.new(0,0)
            dam6 = NumericDamage.new(1,0)

            puts "Damage 1: " + dam1.to_s
            puts "Damage 2: " + dam2.to_s
            puts "Damage 3: " + dam3.to_s
            puts "Damage 4: " + dam4.to_s

            dam4.discardShieldBooster
            puts "Damage 4: " + dam4.to_s
            dam4.discardWeapon(w1)
            puts "Damage 4: " + dam4.to_s
            dam4.discardWeapon(w1)
            puts "Damage 4: " + dam4.to_s
            dam4.discardWeapon(w1)  #no hara nada
            puts "Damage 4: " + dam4.to_s

            if dam4.hasNoEffect
                puts "dam4 no tiene efecto"
            else puts "dam4 si tiene efecto"
            end
            if dam5.hasNoEffect
                puts "dam5 no tiene efecto"
            else puts "dam5 si tiene efecto"
            end

            ######FALTA PROBAR EL ADJUST. he hecho un metodo aparte, esta abjao


            puts "######################## ENEMY ######################## \n"
            e1 = EnemyStarShip.new("enemy1", 1, 1, l1, dam6)
            e2 = EnemyStarShip.newCopy(e1)
            puts "Enemigo 1: " + e1.to_s
            puts "Enemigo 2: " + e2.to_s


            puts "######################## SPACESTATION ######################## \n"
            s1 = SpaceStation.new("Spacestation1",su1)
            #puts s1.to_s

            s1.receiveHangar(h1)
            #puts s1.to_s
            s1.mountWeapon(0)
            #puts s1.to_s
            s1.discardHangar
            puts s1.to_s 
            puts "\n"

            s1.receiveHangar(h2)
            puts s1.to_s 
            puts "\n"
            s1.mountShieldBooster(0)
            puts s1.to_s 
            puts "\n"
            s1.discardHangar
            puts s1.to_s 
            puts "\n"

            puts "fire:"
            puts s1.fire
            puts "protection:"
            puts s1.protection
            puts "speed:"
            puts s1.speed

            s1.setLoot(l2)
            puts s1.to_s 
            puts "\n"

            puts "fire:"
            puts s1.fire
            puts "protection:"
            puts s1.protection
            puts "speed:"
            puts s1.speed

            puts s1.to_s 
            s1.discardWeaponInHangar(0)
            s1.discardWeaponInHangar(0) #no hace nada
            s1.discardShieldBoosterInHangar(0)
            s1.discardShieldBoosterInHangar(0) #no hace nada
            puts s1.to_s 
            s1.discardHangar
            puts s1.to_s 
            s1.discardShieldBooster(0)
            s1.discardWeapon(0)
            puts s1.to_s
            s1.discardShieldBooster(0) #no hace nada
            s1.discardWeapon(0)  #no hace nada

            puts "######################## GAME UNIVERSE ######################## \n"
            gu = GameUniverse.new()
            puts gu.to_s
            names = ["uno", "dos"]
            gu.init(names)
            puts gu.to_s

        end

        def prueba_adjust
            w1 = Weapon.new("W1", WeaponType::LASER, 32)
            w2 = Weapon.new("W2", WeaponType::MISSILE, 5)
            w3 = Weapon.new("W3", WeaponType::PLASMA, 10)
            w4 = Weapon.new("W1", WeaponType::LASER, 32)
            w5 = Weapon.new("W2", WeaponType::MISSILE, 5)
            w6 = Weapon.new("W3", WeaponType::PLASMA, 10)
            arrayW = [w1,w2,w3]
            arrayW2 = [w1, w2, w3, w4, w5, w6]
            arrayW3 = []
            arrayWT = [w1.type,w2.type,w3.type]
            arrayWT2 = [w1.type, w2.type, w3.type, w4.type, w5.type, w6.type]
            arrayWT3 = []
            arrayWT4 = [w1.type]

            sb1 = ShieldBooster.new("SB1", 3.0, 15)
            sb2 = ShieldBooster.new("SB2", 2.5, 50)
            sb3 = ShieldBooster.new("SB3", 1.5, 3)
            arraySB = [sb1, sb2, sb3]
            arraySB2 = []

            dam1 = NumericDamage.new(3,5)
            dam2 = SpecificDamage.new(arrayWT2,10)
            dam3 = NumericDamage.new(0,0)
            dam4 = NumericDamage.new(1,0)
            dam5 = SpecificDamage.new(arrayWT,5)
            dam6 = SpecificDamage.new(arrayWT2,0)
            dam7 = NumericDamage.new(7,0)
            dam8 = NumericDamage.new(0,7)
            dam9 = SpecificDamage.new(arrayWT3,5)
            dam10 = NumericDamage.new(7,1)
            dam11 = SpecificDamage.new(arrayWT2,1)
            dam12 = NumericDamage.new(3,7)
            dam13 = SpecificDamage.new(arrayWT,5)
            dam14 = SpecificDamage.new(arrayWT3,0)
            dam15 = NumericDamage.new(1,1)
            dam16 = SpecificDamage.new(arrayWT4,1)
            dam17 = NumericDamage.new(3,3)
            dam18 = SpecificDamage.new(arrayWT,3)

            #caso 1 / normal, hay de todo bien
            puts "caso 1, hay de todo bien"
            puts dam1.adjust(arrayW, arraySB).to_s #sale un damage de 3 y 3
            puts dam2.adjust(arrayW, arraySB).to_s #sale un damage de 3 sb y las 3 wt del array

            #caso 2, no hay sb
            puts "caso 2, no hay sb"
            puts dam7.adjust(arrayW, arraySB).to_s #sale un damage de 0sb y 3 w
            puts dam6.adjust(arrayW, arraySB).to_s #sale un damage de 0sb y las 3 armas

            #caso 3, no hay weapons
            puts "caso 3, no hay weapons"
            puts dam8.adjust(arrayW, arraySB).to_s #sale un damage de 3sb y 0 w
            puts dam9.adjust(arrayW, arraySB).to_s #sale un damage de 3sb y 0 w

            #caso 4, no hay suficientes sb pero si hay alguno
            puts "caso 4, no hay suficientes sb pero si hay alguno"
            puts dam10.adjust(arrayW, arraySB).to_s #sale un damage de 1sb y 3 w
            puts dam11.adjust(arrayW, arraySB).to_s #sale un damage de 1sb y las 3w

            #caso 5, no hay suficientes weapons pero si hay alguno
            puts "caso 5, no hay suficientes weapons pero si hay alguno"
            puts dam12.adjust(arrayW2, arraySB).to_s #sale un damage de 3 y 3
            puts dam13.adjust(arrayW2, arraySB).to_s #sale un damage de 3 y 3

            #caso 6, no hay de nada (0)
            puts "caso 6, no hay de nada (0)"
            puts dam3.adjust(arrayW, arraySB).to_s #sale un damage vacio
            puts dam14.adjust(arrayW, arraySB).to_s #sale un damage vacio

            #caso 7, no hay suficientes de ninguno de los dos, pero no es vacio
            puts "caso 7, no hay suficientes de ninguno de los dos, pero no es vacio"
            puts dam15.adjust(arrayW, arraySB).to_s #sale un damage de 1sb y 1w
            puts dam16.adjust(arrayW, arraySB).to_s #sale un damage de 1sb y un weapon de tipo laser

            #caso 8, los array por parametros estan vacios
            puts "caso 8, los array por parametros estan vacios"
            puts dam1.adjust(arrayW3, arraySB2).to_s #sale un damage vacio
            puts dam2.adjust(arrayW3, arraySB2).to_s #sale un damage vacio

            #caso9, se tiene justo lo que pide
            puts dam17.adjust(arrayW, arraySB).to_s #sale un damage de 3sb y 3w
            puts dam18.adjust(arrayW, arraySB).to_s #sale un damage de 3sb y 3w


            #EXTRA: 
            arrayW4 = []
            for i in 0..@@NUM_ITERACCIONES-1 do
                nombre = "Weapon "+i.to_s
                arrayW4.push(Weapon.new(nombre, WeaponType::LASER, 32))
                puts " "
            end

            arrayW4.each{ |w_arr|
                #puts w_arr.to_s
            }
        end
    end

    testpractica2 = TestP2.new()
    puts testpractica2.main
    puts testpractica2.prueba_adjust

end





