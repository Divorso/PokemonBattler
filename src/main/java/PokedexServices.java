import java.util.ArrayList;
import java.util.Scanner;

public class PokedexServices {
    public static void showAllPokemon(ArrayList<Pokemon> pokemonArray, String choice){

        if(pokemonArray.isEmpty()){
            System.out.println("Du har inga Pokemon");
        } else {
            if(choice.equalsIgnoreCase("all")){
                for(Pokemon pokemon : pokemonArray){
                    System.out.printf("Namn: %s, max HP: %d, nuvarande HP %d, Pokemon typ: %s \n",
                            pokemon.getName(),pokemon.getMaxHP(),pokemon.getCurrentHP(),pokemon.getType());

                    for(Attack attack : pokemon.getAttacks()){
                        System.out.printf("Attack name: %s, base damage: %d, accuracy: %d, attack: %s\n",
                                attack.getAttackName(),
                                attack.getBaseDamage(),
                                attack.getAccuracy(),
                                attack.getType()
                        );
                    }
                    Graphics.getEmptySpace(2);

                }

            } else if (choice.equalsIgnoreCase("name")) {
                for(Pokemon pokemon : pokemonArray){
                    System.out.printf("Namn: %s \n", pokemon.getName());
                }
            }

        }
    }
    public static void addPokemon(ArrayList<Pokemon> pokemonArray, Scanner scanner){
        Graphics.getAddPokemonGraphics();
        System.out.print("----Vad ska din Pokemon heta----: ");
        String name = ErrorHandelingService.checkNameLettersOnly(scanner);

        System.out.println("----Vad är maxHP på din Pokemon----");
        System.out.print("----(1-500)----: ");
        int maxHP = ErrorHandelingService.checkIntMinMax(scanner,1,500);

        System.out.println("--Vad är din Pokemon för typ välj mellan--");
        System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
        Type type = ErrorHandelingService.checkType(scanner);

        //lägger till ny pokemon
        Pokemon newPokemon = new Pokemon();
        newPokemon.setName(name);
        newPokemon.setMaxHP(maxHP);
        newPokemon.setCurrentHP(maxHP);
        newPokemon.setType(type);

        System.out.println("----Hur många attacker har din Pokemon?----");
        System.out.print("----(Du får ha mellan 1-4 attacker)----: ");
        int amountAttacks = ErrorHandelingService.checkIntMinMax(scanner,1,4 );
        ArrayList<Attack> attackArray = new ArrayList<>();
        for(int i = 0; i < amountAttacks; i++ ){
            Graphics.getEmptySpace(25);
            Graphics.getAttackGraphics();
            String numberToText = "";
            switch (i){
                case 0 -> numberToText = "första";
                case 1 -> numberToText = "andra";
                case 2 -> numberToText = "tredje";
                case 3 -> numberToText = "fjärde";
            }
            System.out.printf("Vad heter %s attacken: ", numberToText);
            String attackName = ErrorHandelingService.checkNameLettersOnly(scanner);
            System.out.print("Hur mycket skada gör den (1-40): ");
            int attackDamage = ErrorHandelingService.checkIntMinMax(scanner,1,40);
            System.out.print("Hur träffsäker är den (1-100): ");
            int accuracy = ErrorHandelingService.checkIntMinMax(scanner,1,100);
            System.out.println("Vad är det för typ av attack?");
            System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
            Type typeAttack = ErrorHandelingService.checkType(scanner);

            Attack newAttack = new Attack();
            newAttack.setAttackName(attackName);
            newAttack.setBaseDamage(attackDamage);
            newAttack.setAccuracy(accuracy);
            newAttack.setType(typeAttack);
            attackArray.add(newAttack);

        }
        pokemonArray.add(newPokemon);
        newPokemon.setAttacks(attackArray);
        Graphics.getPokemonCreatedGraphics();
    }
    public static void removePokemon(Scanner scanner, ArrayList<Pokemon> pokemonArray){
        if(pokemonArray.isEmpty()){
            System.out.println("Du har inga Pokemon att ta bort");
            Graphics.getReturnInMenu(scanner);
        } else {
            showAllPokemon(pokemonArray, "name");
            System.out.println("Vilken Pokemon vill du ta bort?");
            System.out.println("Skriv B för att backa");
            System.out.print("Pokemon: ");
            String pokemonToRemove = ErrorHandelingService.checkForPokemon(scanner,pokemonArray);
            if(!pokemonToRemove.equalsIgnoreCase("b")){
                for (int i = 0; i < pokemonArray.size(); i++) {

                    if (pokemonArray.get(i).getName().equalsIgnoreCase(pokemonToRemove)) {
                        System.out.printf("Är du säker på att du vill ta bort %s \n", pokemonToRemove);
                        System.out.print("Y/N: ");
                        String userChoice = ErrorHandelingService.checkNameLettersOnly(scanner);
                        if (userChoice.equalsIgnoreCase("Y")){
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
    public static void updatePokemon(Scanner scanner, ArrayList<Pokemon> pokemonArray){
        showAllPokemon(pokemonArray,"name");
        System.out.print("Vilken Pokemon vill du uppdatera: ");
        String pokemonToEdit = ErrorHandelingService.checkForPokemon(scanner,pokemonArray);
        if(!pokemonToEdit.equalsIgnoreCase("b")) {
            for (Pokemon pokemon : pokemonArray) {
                if (pokemon.getName().equalsIgnoreCase(pokemonToEdit)) {
                    System.out.printf("Vad vill du döpa %s om till: ", pokemon.getName());
                    String updatedName = ErrorHandelingService.checkNameLettersOnly(scanner);

                    System.out.printf("Nuvarande MAX HP är %d, vad blir nya MAX HP \n", pokemon.getMaxHP());
                    System.out.print("----(1-500)----: ");
                    int updateMaxHP = ErrorHandelingService.checkIntMinMax(scanner, 1, 500);

                    System.out.printf("Nuvarande typ är '%s' vad blir nya 'typ': ", pokemon.getType());
                    Type updatedType = ErrorHandelingService.checkType(scanner);

                    pokemon.setName(updatedName);
                    pokemon.setMaxHP(updateMaxHP);
                    pokemon.setType(updatedType);

                    boolean isRunning = true;
                    while (isRunning) {
                        System.out.println("1 - Uppdatera nuvarande attacker");
                        System.out.println("2 - Lägg till nya attacker");
                        System.out.println("3 - Ta bort en attack");
                        System.out.println("4 - Avsluta");
                        int userChoice = ErrorHandelingService.checkIntMinMax(scanner, 1, 4);
                        switch (userChoice) {
                            case 1 -> {
                                for (int y = 0; y < pokemon.getAttacks().size(); y++) {
                                    Graphics.getEmptySpace(20);
                                    Graphics.getUpdatedAttackGraphics();
                                    String numberToText = "";
                                    switch (y) {
                                        case 0 -> numberToText = "första";
                                        case 1 -> numberToText = "andra";
                                        case 2 -> numberToText = "tredje";
                                        case 3 -> numberToText = "fjärde";
                                    }
                                    System.out.printf("----Updatering av din %s attack----\n", numberToText);

                                    System.out.printf("Din nuvarande attack heter %s, vad vill du döpa om den till: ",
                                            pokemon.getAttacks().get(y).getAttackName());
                                    String updatedAttackName = ErrorHandelingService.checkNameLettersOnly(scanner);

                                    System.out.printf("Din nuvarande bas skada är %d, vad vill du uppdatera den till: \n",
                                            pokemon.getAttacks().get(y).getBaseDamage());
                                    System.out.print("----(1-40)----: ");
                                    int updatedBaseDamage = ErrorHandelingService.checkIntMinMax(scanner, 1, 40);

                                    System.out.printf("Din nuvarande träffsäkerhet är %d, vad vill du uppdatera den till: \n",
                                            pokemon.getAttacks().get(y).getAccuracy());
                                    System.out.print("----(1-100)----: ");
                                    int updatedAccuracy = ErrorHandelingService.checkIntMinMax(scanner, 1, 100);

                                    System.out.printf("Din nuvarande attack typ är %s, vad vill du uppdatera den till: ",
                                            pokemon.getAttacks().get(y).getType());
                                    Type updatedAttackType = ErrorHandelingService.checkType(scanner);

                                    pokemon.getAttacks().get(y).setAttackName(updatedAttackName);
                                    pokemon.getAttacks().get(y).setBaseDamage(updatedBaseDamage);
                                    pokemon.getAttacks().get(y).setAccuracy(updatedAccuracy);
                                    pokemon.getAttacks().get(y).setType(updatedAttackType);
                                }
                            }
                            case 2 -> {
                                int currentAttacks = pokemon.getAttacks().size();
                                int availableSlots = 4 - currentAttacks;

                                if (currentAttacks == 4) {
                                    System.out.println("Din attacklista är full. Du kan max ha 4 attacker.");
                                } else {
                                    System.out.printf("Din Pokemon har %d attacker. Du kan lägga till max %d till.\n", currentAttacks, availableSlots);
                                    System.out.print("Hur många attacker vill du lägga till: ");

                                    int userChoiceAttacks = ErrorHandelingService.checkIntMinMax(scanner, 1, availableSlots);

                                    for (int iAttack = 0; iAttack < userChoiceAttacks; iAttack++) {
                                        Graphics.getEmptySpace(20);
                                        Graphics.getAttackGraphics();
                                        System.out.print("Vad heter attacken: ");
                                        String attackName = ErrorHandelingService.checkNameLettersOnly(scanner);
                                        System.out.print("Hur mycket skada gör den (1-40): ");

                                        int attackDamage = ErrorHandelingService.checkIntMinMax(scanner, 1, 40);

                                        System.out.print("Hur träffsäker är den (1-100): ");
                                        int accuracy = ErrorHandelingService.checkIntMinMax(scanner, 1, 100);

                                        System.out.println("Vad är det för typ av attack?");
                                        Type typeAttack = ErrorHandelingService.checkType(scanner);

                                        Attack newAttack = new Attack();

                                        newAttack.setAttackName(attackName);
                                        newAttack.setBaseDamage(attackDamage);
                                        newAttack.setAccuracy(accuracy);
                                        newAttack.setType(typeAttack);

                                        pokemon.getAttacks().add(newAttack);
                                    }
                                }
                            }
                            case 3 -> {
                                Graphics.getEmptySpace(20);
                                Graphics.getAttackRemoveGraphics();
                                if (pokemon.getAttacks().size() <= 1) {
                                    System.out.println("En Pokemon måste ha minst 1 attack!");
                                } else {
                                    for (Attack attack : pokemon.getAttacks()) {
                                        System.out.printf("Attack: %s%n", attack.getAttackName());
                                    }

                                    System.out.print("Vilken attack vill du ta bort: ");
                                    String removeAttack =
                                            ErrorHandelingService.checkNameLettersOnly(scanner);

                                    boolean attackRemoved = pokemon.getAttacks().removeIf(
                                            attack -> attack.getAttackName().equalsIgnoreCase(removeAttack));

                                    if (attackRemoved) {
                                        System.out.println("Attacken är nu borttagen.");
                                    } else {
                                        System.out.printf("%s finns ej.%n", removeAttack);
                                    }
                                }
                            }
                            case 4 -> {
                                isRunning = false;
                            }
                        }

                    }
                }
            }
        }

    }
    public static void seedPokemon(ArrayList<Pokemon> pokemonArrayList){
        if(pokemonArrayList.isEmpty()){
            Pokemon growlithe = new Pokemon();

            growlithe.setName("Growlithe");
            growlithe.setMaxHP(115);
            growlithe.setCurrentHP(115);
            growlithe.setType(Type.FIRE);

            ArrayList<Attack> growlitheAttacks = new ArrayList<>();

            Attack flameWheel = new Attack();
            flameWheel.setAttackName("Flame Wheel");
            flameWheel.setBaseDamage(35);
            flameWheel.setAccuracy(90);
            flameWheel.setType(Type.FIRE);

            Attack bite = new Attack();
            bite.setAttackName("Bite");
            bite.setBaseDamage(25);
            bite.setAccuracy(95);
            bite.setType(Type.PHYSICAL);

            growlitheAttacks.add(flameWheel);
            growlitheAttacks.add(bite);

            growlithe.setAttacks(growlitheAttacks);
            pokemonArrayList.add(growlithe);


            Pokemon psyduck = new Pokemon();
            psyduck.setName("Psyduck");
            psyduck.setMaxHP(125);
            psyduck.setCurrentHP(125);
            psyduck.setType(Type.WATER);

            ArrayList<Attack> psyduckAttacks = new ArrayList<>();

            Attack waterPulse = new Attack();
            waterPulse.setAttackName("Water Pulse");
            waterPulse.setBaseDamage(40);
            waterPulse.setAccuracy(90);
            waterPulse.setType(Type.WATER);

            Attack scratchPsyduck = new Attack();
            scratchPsyduck.setAttackName("Scratch");
            scratchPsyduck.setBaseDamage(25);
            scratchPsyduck.setAccuracy(100);
            scratchPsyduck.setType(Type.PHYSICAL);

            psyduckAttacks.add(waterPulse);
            psyduckAttacks.add(scratchPsyduck);

            psyduck.setAttacks(psyduckAttacks);
            pokemonArrayList.add(psyduck);


            Pokemon oddish = new Pokemon();
            oddish.setName("Oddish");
            oddish.setMaxHP(100);
            oddish.setCurrentHP(100);
            oddish.setType(Type.GRASS);

            ArrayList<Attack> oddishAttacks = new ArrayList<>();

            Attack absorb = new Attack();
            absorb.setAttackName("Absorb");
            absorb.setBaseDamage(30);
            absorb.setAccuracy(95);
            absorb.setType(Type.GRASS);

            Attack razorLeaf = new Attack();
            razorLeaf.setAttackName("Razor Leaf");
            razorLeaf.setBaseDamage(40);
            razorLeaf.setAccuracy(90);
            razorLeaf.setType(Type.GRASS);

            oddishAttacks.add(absorb);
            oddishAttacks.add(razorLeaf);

            oddish.setAttacks(oddishAttacks);
            pokemonArrayList.add(oddish);


            Pokemon magnemite = new Pokemon();
            magnemite.setName("Magnemite");
            magnemite.setMaxHP(90);
            magnemite.setCurrentHP(90);
            magnemite.setType(Type.ELECTRIC);

            ArrayList<Attack> magnemiteAttacks = new ArrayList<>();

            Attack thunderWave = new Attack();
            thunderWave.setAttackName("Thunder Wave");
            thunderWave.setBaseDamage(30);
            thunderWave.setAccuracy(90);
            thunderWave.setType(Type.ELECTRIC);

            Attack spark = new Attack();
            spark.setAttackName("Spark");
            spark.setBaseDamage(40);
            spark.setAccuracy(95);
            spark.setType(Type.ELECTRIC);

            magnemiteAttacks.add(thunderWave);
            magnemiteAttacks.add(spark);

            magnemite.setAttacks(magnemiteAttacks);
            pokemonArrayList.add(magnemite);


            Pokemon snorunt = new Pokemon();
            snorunt.setName("Snorunt");
            snorunt.setMaxHP(105);
            snorunt.setCurrentHP(105);
            snorunt.setType(Type.ICE);

            ArrayList<Attack> snoruntAttacks = new ArrayList<>();

            Attack iceShard = new Attack();
            iceShard.setAttackName("Ice Shard");
            iceShard.setBaseDamage(35);
            iceShard.setAccuracy(100);
            iceShard.setType(Type.ICE);

            Attack icyWind = new Attack();
            icyWind.setAttackName("Icy Wind");
            icyWind.setBaseDamage(40);
            icyWind.setAccuracy(90);
            icyWind.setType(Type.ICE);

            snoruntAttacks.add(iceShard);
            snoruntAttacks.add(icyWind);

            snorunt.setAttacks(snoruntAttacks);
            pokemonArrayList.add(snorunt);


            Pokemon meowth = new Pokemon();
            meowth.setName("Meowth");
            meowth.setMaxHP(100);
            meowth.setCurrentHP(100);
            meowth.setType(Type.PHYSICAL);

            ArrayList<Attack> meowthAttacks = new ArrayList<>();

            Attack scratchMeowth = new Attack();
            scratchMeowth.setAttackName("Scratch");
            scratchMeowth.setBaseDamage(25);
            scratchMeowth.setAccuracy(100);
            scratchMeowth.setType(Type.PHYSICAL);

            Attack slash = new Attack();
            slash.setAttackName("Slash");
            slash.setBaseDamage(40);
            slash.setAccuracy(95);
            slash.setType(Type.PHYSICAL);

            meowthAttacks.add(scratchMeowth);
            meowthAttacks.add(slash);

            meowth.setAttacks(meowthAttacks);
            pokemonArrayList.add(meowth);
        }


    }
};
