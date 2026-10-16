import java.util.ArrayList;
import java.util.Scanner;

public class PokedexServices {
    GraphicsSelector graphics = new Graphics();

    public void showAllPokemon(ArrayList<Pokemon> pokemonArray, String choice) {
        if (pokemonArray.isEmpty()) {
            System.out.println("Du har inga Pokemon");
        } else {

            switch (choice.toLowerCase()) {
                case "all" -> {
                    for (Pokemon pokemon : pokemonArray) {
                        System.out.printf("Namn: %s, max HP: %d, nuvarande HP %d, Pokemon typ: %s \n",
                                pokemon.getName(), pokemon.getMaxHP(), pokemon.getCurrentHP(), pokemon.getType());

                        for (Attack attack : pokemon.getAttacks()) {
                            System.out.printf("Attack name: %s, base damage: %d, accuracy: %d, attack: %s\n",
                                    attack.getAttackName(),
                                    attack.getBaseDamage(),
                                    attack.getAccuracy(),
                                    attack.getType()
                            );
                        }
                        graphics.getEmptySpace(2);
                    }

                }
                case "battle" -> {
                    for (Pokemon pokemon : pokemonArray) {
                        if (pokemon.isDefeated()) {
                            System.out.printf("Namn: %s är besegrad\n", pokemon.getName());
                        } else {
                            System.out.printf("Namn: %s , NuvarandeHP: %s\n", pokemon.getName(), pokemon.getCurrentHP());
                        }
                    }
                }
                case "name" -> {
                    for (Pokemon pokemon : pokemonArray) {
                        System.out.printf("Namn: %s \n", pokemon.getName());
                    }
                }
            }
        }
    }

    public void addPokemon(ArrayList<Pokemon> pokemonArray, Scanner scanner) {
        graphics.getMessage("Lägg till Pokemon i din Pokeindex");

        System.out.print("----Vad ska din Pokemon heta----: ");
        String name = ValidationService.checkPokemonName(scanner);

        System.out.println("----Vad är maxHP på din Pokemon----");
        System.out.printf("----(%d-%d)----: \n", Pokemon.MIN_HP, Pokemon.MAX_HP);
        int maxHP = ValidationService.checkIntMinMax(scanner, Pokemon.MIN_HP, Pokemon.MAX_HP);

        System.out.println("--Vad är din Pokemon för typ välj mellan--");
        System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
        Type type = ValidationService.checkType(scanner);


        System.out.println("----Hur många attacker har din Pokemon?----");
        System.out.print("----(Du får ha mellan 1-4 attacker)----: ");
        int amountAttacks = ValidationService.checkIntMinMax(scanner, Attack.MIN_ATTACKS, Attack.MAX_ATTACKS);
        ArrayList<Attack> attackArray = new ArrayList<>();


        for (int i = 0; i < amountAttacks; i++) {
            graphics.getEmptySpace(40);
            graphics.getMessage("Lägg till Attack");

            System.out.printf("\nVad heter %s attacken: ", ValidationService.numberToText(i));
            String attackName = ValidationService.checkAttackName(scanner);

            System.out.printf("\nHur mycket skada gör den (%d-%d):", Attack.MIN_DAMAGE, Attack.MAX_DAMAGE);
            int attackDamage = ValidationService.checkIntMinMax(scanner, Attack.MIN_DAMAGE, Attack.MAX_DAMAGE);

            System.out.printf("\nHur träffsäker är den (%d-%d): \n", Attack.MIN_ACCURACY, Attack.MAX_ACCURACY);
            int accuracy = ValidationService.checkIntMinMax(scanner, Attack.MIN_ACCURACY, Attack.MAX_ACCURACY);

            System.out.println("Vad är det för typ av attack?");
            System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
            Type typeAttack = ValidationService.checkType(scanner);

            Attack newAttack = new Attack(attackName, attackDamage, accuracy, typeAttack);
            attackArray.add(newAttack);

        }
        Pokemon newPokemon = new Pokemon(name, maxHP, type, attackArray);
        pokemonArray.add(newPokemon);
        graphics.getMessage("Din Pokemon är nu skapad");
    }

    public void removePokemon(Scanner scanner, ArrayList<Pokemon> pokemonArray) {
        if (pokemonArray.isEmpty()) {
            System.out.println("Du har inga Pokemon att ta bort");
            ValidationService.getReturnInMenu(scanner);
        } else {
            showAllPokemon(pokemonArray, "name");
            System.out.println("""
                    Vilken Pokemon vill du ta bort?
                    Skriv B för att backa
                    """);
            System.out.print("Pokemon: ");

            String pokemonToRemove = ValidationService.checkForPokemon(scanner, pokemonArray);
            if (!pokemonToRemove.equalsIgnoreCase("b")) {
                for (int i = 0; i < pokemonArray.size(); i++) {

                    if (pokemonArray.get(i).getName().equalsIgnoreCase(pokemonToRemove)) {
                        System.out.printf("Är du säker på att du vill ta bort %s \n", pokemonToRemove);
                        System.out.print("Y/N: ");
                        String userChoice = ValidationService.checkNameLettersOnly(scanner);
                        if (userChoice.equalsIgnoreCase("Y")) {
                            pokemonArray.remove(i);
                            System.out.println("Pokemon borttagen!");
                            return;
                        } else {
                            System.out.println("Fel val");
                        }
                    }
                }
            }

        }


    }

    public void updatePokemon(Scanner scanner, ArrayList<Pokemon> pokemonArray) {

        if (pokemonArray.isEmpty()) {
            System.out.println("Du har inga Pokemon att uppdatera");
            return;
        }

        while (true) {
            graphics.getEmptySpace(50);
            graphics.getMessage("Uppdatera Pokemon");
            showAllPokemon(pokemonArray, "name");

            System.out.println("Skriv 'b' för att backa");
            System.out.print("Vilken Pokemon vill du uppdatera: ");

            String pokemonToEdit = ValidationService.checkForPokemon(scanner, pokemonArray);

            if (pokemonToEdit.equalsIgnoreCase("b")) {
                return;
            }

            Pokemon pokemon = findPokemon(pokemonToEdit, pokemonArray);

            while (true) {

                graphics.getEmptySpace(50);
                graphics.getMessage("Administrera Pokemon");

                System.out.printf("Vald Pokemon: %s (maxHP: %d, typ: %s) %n",
                        pokemon.getName(),
                        pokemon.getMaxHP(),
                        pokemon.getType());

                System.out.println("""
                    1) Ändra namn
                    2) Ändra maxHP
                    3) Ändra typ
                    4) Ändra attacker
                    5) Backa""");
                System.out.print("Val: ");
                int userChoice = ValidationService.checkIntMinMax(scanner, 1, 5);

                switch (userChoice) {

                    case 1 -> {
                        System.out.print("Vad ska din Pokemon heta: ");
                        String updatedName = ValidationService.checkPokemonName(scanner);

                        pokemon.setName(updatedName);
                        System.out.println("Namnet har uppdaterats!");
                    }

                    case 2 -> {
                        System.out.printf("Nytt maxHP (%d-%d): ",
                                Pokemon.MIN_HP, Pokemon.MAX_HP);

                        int updatedMaxHP = ValidationService.checkIntMinMax(
                                scanner, Pokemon.MIN_HP, Pokemon.MAX_HP);

                        pokemon.setMaxHP(updatedMaxHP);
                        System.out.println("MaxHP har uppdaterats!");
                    }

                    case 3 -> {
                        System.out.println("Välj Pokemon-typ:");
                        System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");

                        Type updatedType = ValidationService.checkType(scanner);

                        pokemon.setType(updatedType);
                        System.out.println("Typen har uppdaterats!");
                    }

                    case 4 -> {
                        manageAttacks(pokemon, scanner);
                    }

                    case 5 -> {
                        break;
                    }
                }

                if (userChoice == 5) {
                    break;
                }
            }
        }
    }




    public static void seedPokemon(ArrayList<Pokemon> pokemonArrayList) {

        if (pokemonArrayList.isEmpty()) {
            ArrayList<Attack> growlitheAttacks = new ArrayList<>();

            growlitheAttacks.add(
                    new Attack("Flame Wheel", 35, 90, Type.FIRE)
            );

            growlitheAttacks.add(
                    new Attack("Bite", 25, 95, Type.PHYSICAL)
            );

            Pokemon growlithe = new Pokemon(
                    "Growlithe",
                    115,
                    Type.FIRE,
                    growlitheAttacks
            );
            pokemonArrayList.add(growlithe);


            ArrayList<Attack> psyduckAttacks = new ArrayList<>();

            psyduckAttacks.add(
                    new Attack("Water Pulse", 40, 90, Type.WATER)
            );
            psyduckAttacks.add(
                    new Attack("Scratch", 25, 100, Type.PHYSICAL)
            );

            Pokemon psyduck = new Pokemon(
                    "Psyduck",
                    125,
                    Type.WATER,
                    psyduckAttacks
            );
            pokemonArrayList.add(psyduck);


            ArrayList<Attack> oddishAttacks = new ArrayList<>();

            oddishAttacks.add(
                    new Attack("Absorb", 30, 95, Type.GRASS)
            );
            oddishAttacks.add(
                    new Attack("Razor Leaf", 40, 90, Type.GRASS)
            );

            Pokemon oddish = new Pokemon(
                    "Oddish",
                    100,
                    Type.GRASS,
                    oddishAttacks
            );
            pokemonArrayList.add(oddish);


            ArrayList<Attack> magnemiteAttacks = new ArrayList<>();

            magnemiteAttacks.add(
                    new Attack("Thunder Wave", 30, 90, Type.ELECTRIC)
            );
            magnemiteAttacks.add(
                    new Attack("Spark", 40, 95, Type.ELECTRIC)
            );

            Pokemon magnemite = new Pokemon(
                    "Magnemite",
                    90,
                    Type.ELECTRIC,
                    magnemiteAttacks
            );
            pokemonArrayList.add(magnemite);


            ArrayList<Attack> snoruntAttacks = new ArrayList<>();

            snoruntAttacks.add(
                    new Attack("Ice Shard", 35, 100, Type.ICE)
            );
            snoruntAttacks.add(
                    new Attack("Icy Wind", 40, 90, Type.ICE)
            );

            Pokemon snorunt = new Pokemon(
                    "Snorunt",
                    105,
                    Type.ICE,
                    snoruntAttacks
            );

            pokemonArrayList.add(snorunt);


            ArrayList<Attack> meowthAttacks = new ArrayList<>();

            meowthAttacks.add(
                    new Attack("Scratch", 25, 100, Type.PHYSICAL)
            );
            meowthAttacks.add(
                    new Attack("Slash", 40, 95, Type.PHYSICAL)
            );

            Pokemon meowth = new Pokemon(
                    "Meowth",
                    100,
                    Type.PHYSICAL,
                    meowthAttacks
            );
            pokemonArrayList.add(meowth);
        }
    }

    public static Pokemon findPokemon(String name, ArrayList<Pokemon> pokemonArray) {

        for (Pokemon pokemon : pokemonArray) {
            if (pokemon.getName().equalsIgnoreCase(name)) {
                return pokemon;
            }
        }
        throw new InvalidPokemonException();
    }




    public void updateAttack(Pokemon pokemon, Scanner scanner) {
        for (int i = 0; i < pokemon.getAttacks().size(); i++) {
            graphics.getEmptySpace(50);

            graphics.getMessage("Uppdatera attack");
            System.out.printf("----Updatering av din %s attack----\n", ValidationService.numberToText(i));

            System.out.printf("Din nuvarande attack heter %s, vad vill du döpa om den till: ",
                    pokemon.getAttacks().get(i).getAttackName());
            String updatedAttackName = ValidationService.checkAttackName(scanner);

            System.out.printf("Din nuvarande bas skada är %d, vad vill du uppdatera den till: \n",
                    pokemon.getAttacks().get(i).getBaseDamage());
            System.out.print("----(1-40)----: ");
            int updatedBaseDamage = ValidationService.checkIntMinMax(scanner, 1, 40);

            System.out.printf("Din nuvarande träffsäkerhet är %d, vad vill du uppdatera den till: \n",
                    pokemon.getAttacks().get(i).getAccuracy());
            System.out.print("----(1-100)----: ");
            int updatedAccuracy = ValidationService.checkIntMinMax(scanner, 1, 100);

            System.out.printf("Din nuvarande attack typ är %s, vad vill du uppdatera den till: ",
                    pokemon.getAttacks().get(i).getType());
            System.out.print("\n(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
            Type updatedAttackType = ValidationService.checkType(scanner);

            Attack updatedAttack = new Attack(updatedAttackName, updatedBaseDamage, updatedAccuracy, updatedAttackType);
            pokemon.getAttacks().set(i, updatedAttack);
        }

    }

    public void addAttack(Pokemon pokemon, Scanner scanner) {

        int currentAttacks = pokemon.getAttacks().size();
        int availableSlots = Attack.MAX_ATTACKS - currentAttacks;

        if (availableSlots <= 0) {
            System.out.println("Din attacklista är full. Du kan max ha 4 attacker.");
            ValidationService.getReturnInMenu(scanner);
            return;
        }

        System.out.print("Hur många attacker vill du lägga till: ");
        int userChoiceAttacks = ValidationService.checkIntMinMax(scanner, 1, availableSlots);

        for (int i = 0; i < userChoiceAttacks; i++) {
            Attack newAttack = createAttack(scanner);
            pokemon.getAttacks().add(newAttack);

        }


    }

    public void removeAttack(Pokemon pokemon, Scanner scanner) {
        graphics.getEmptySpace(50);
        graphics.getMessage("Ta bort attack");

        if (pokemon.getAttacks().size() <= 1) {
            getAttackList(pokemon);
            System.out.println("Pokemon måste ha minst 1 attack!");
            ValidationService.getReturnInMenu(scanner);
            return;
        }

        getAttackList(pokemon);

        System.out.print("Vilken attack vill du ta bort (ange nummer): ");

        int userChoice = ValidationService.checkIntMinMax(scanner, 1, pokemon.getAttacks().size());

        Attack removedAttack = pokemon.getAttacks().remove(userChoice - 1);

        System.out.printf("Attacken %s är nu borttagen!%n", removedAttack.getAttackName());
    }

    private Attack createAttack(Scanner scanner) {
        graphics.getEmptySpace(50);
        graphics.getMessage("Lägg till Attack");

        System.out.print("Vad heter attacken: ");
        String attackName = ValidationService.checkAttackName(scanner);

        System.out.printf("Hur mycket skada gör den (%d-%d): ", Attack.MIN_DAMAGE, Attack.MAX_DAMAGE);
        int attackDamage = ValidationService.checkIntMinMax(scanner, Attack.MIN_DAMAGE, Attack.MAX_DAMAGE);

        System.out.printf("\nHur träffsäker är den (%d-%d): ", Attack.MIN_ACCURACY, Attack.MAX_ACCURACY);
        int accuracy = ValidationService.checkIntMinMax(scanner, Attack.MIN_ACCURACY, Attack.MAX_ACCURACY);

        System.out.println("Vad är det för typ av attack?");
        System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
        Type typeAttack = ValidationService.checkType(scanner);

        return new Attack(attackName, attackDamage, accuracy, typeAttack);

    }

    private void getAttackList(Pokemon pokemon) {
        for (int i = 0; i < pokemon.getAttacks().size(); i++) {
            System.out.printf("Attack: %d - Name: %s%n",
                    i + 1,
                    pokemon.getAttacks().get(i).getAttackName());
        }
    }

    private void manageAttacks(Pokemon pokemon, Scanner scanner) {

        while (true) {
            graphics.getEmptySpace(50);
            graphics.getMessage("Administrera dina attacker");
            System.out.println("Pokemon: " + pokemon.getName());
            getAttackList(pokemon);
            System.out.println("=".repeat(80));
            System.out.println("""
                1 - Uppdatera nuvarande attacker
                2 - Lägg till nya attacker
                3 - Ta bort en attack
                4 - Backa""");

            System.out.print("Val: ");

            int userAttackChoice = ValidationService.checkIntMinMax(scanner, 1, 4);
            switch (userAttackChoice) {
                case 1 -> updateAttack(pokemon, scanner);
                case 2 -> addAttack(pokemon, scanner);
                case 3 -> removeAttack(pokemon, scanner);
                case 4 -> {
                    return;
                }
            }
        }
    }


};
