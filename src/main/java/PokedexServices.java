import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PokedexServices {
    public static void showAllPokemon(ArrayList<Pokemon> pokemonArray, String choice){
        if(pokemonArray.isEmpty()){
            System.out.println("Du har inga Pokemon");
        } else {

            switch (choice.toLowerCase()){
                case "all" -> {
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

                }
                case "battle" -> {
                    for(Pokemon pokemon : pokemonArray){
                        System.out.printf("Namn: %s , NuvarandeHP: %s \n", pokemon.getName(),pokemon.getCurrentHP());
                    }
                }
                case "name" -> {
                    for(Pokemon pokemon : pokemonArray){
                        System.out.printf("Namn: %s \n", pokemon.getName());
                    }
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

            Attack newAttack = new Attack(attackName,attackDamage,accuracy,typeAttack);
            attackArray.add(newAttack);

        }
        Pokemon newPokemon = new Pokemon(name, maxHP, type,attackArray);
        pokemonArray.add(newPokemon);
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

                                    Attack updatedAttack = new Attack(updatedAttackName,updatedBaseDamage,updatedAccuracy,updatedAttackType);
                                    pokemon.getAttacks().set(y,updatedAttack);
                                }
                            }
                            case 2 -> {
                                int currentAttacks = pokemon.getAttacks().size();
                                int availableSlots = 4 - currentAttacks;

                                if (currentAttacks == 4) {
                                    System.out.println("Din attacklista är full. Du kan max ha 4 attacker.");
                                } else {
                                    System.out.printf("Din Pokemon har redan %d attacker. Du kan lägga till max %d till.\n", currentAttacks, availableSlots);
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

                                        Attack newAttack = new Attack(attackName,attackDamage,accuracy,typeAttack);

                                        pokemon.getAttacks().add(newAttack);
                                    }
                                }
                            }
                            case 3 -> {
                                Graphics.getEmptySpace(20);
                                Graphics.getAttackRemoveGraphics();

                                if (pokemon.getAttacks().size() <= 1) {
                                    System.out.println("Pokemon måste ha minst 1 attack!");
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
};
