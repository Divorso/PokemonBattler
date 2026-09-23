import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class pokedexServices {
    public static void showAllPokemons(ArrayList<Pokemon> pokemonArray, String choice){

        if(pokemonArray.size() == 0){
            System.out.println("Du har inga Pokemon");
        } else {
            if(choice.equalsIgnoreCase("all")){
                for(Pokemon pokemon : pokemonArray){
                    System.out.printf("Namn: %s, max HP: %d, nuvarande HP %d, Pokemon typ: %s \n",
                            pokemon.getName(),pokemon.getMaxHP(),pokemon.getCurrentHP(),pokemon.getType());
                    /*
                    for(Attack attack : pokemon.getAttacks()){
                        System.out.printf("Attack name: %s, base damage: %d, accuracy: %d, attack: %s\n",
                                attack.getAttackName(),
                                attack.getBaseDamage(),
                                attack.getAccuracy(),
                                attack.getType()
                        );
                    }

                     */
                }

            } else if (choice.equalsIgnoreCase("name")) {
                for(Pokemon pokemon : pokemonArray){
                    System.out.printf("Namn: %s \n", pokemon.getName());
                }
            }

        }
    }
    public static void addPokemon(ArrayList<Pokemon> pokemonArray, Scanner scanner){
        System.out.println("");
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
            System.out.printf("Vad heter din %s attacken: ", numberToText);
            String attackName = ErrorHandelingService.checkNameLettersOnly(scanner);
            System.out.print("Hur mycket skada gör den (1-40): ");
            int attackDamage = ErrorHandelingService.checkIntMinMax(scanner,1,40);
            System.out.print("Hur träffsäker är den (1-100): ");
            int accuracy = ErrorHandelingService.checkIntMinMax(scanner,1,100);
            System.out.println("Vad är det för typ av attack?");
            System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
            Type typeAttack = ErrorHandelingService.checkType(scanner);

            //lägger till ny attack
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
        if(pokemonArray.size() == 0){
            System.out.println("Du har inga Pokemon att ta bort");
            Graphics.getReturnInMenu(scanner);
        } else {
            showAllPokemons(pokemonArray, "name");
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
        showAllPokemons(pokemonArray,"name");
        System.out.print("Vilken Pokemon vill du uppdatera: ");
        String pokemonToEdit = ErrorHandelingService.checkForPokemon(scanner,pokemonArray);
        if(!pokemonToEdit.equalsIgnoreCase("b")) {
            for (int i = 0; i < pokemonArray.size(); ++i) {
                if (pokemonArray.get(i).getName().equalsIgnoreCase(pokemonToEdit)) {
                    System.out.printf("Vad vill du döpa %s om till: ", pokemonArray.get(i).getName());
                    String updatedName = ErrorHandelingService.checkNameLettersOnly(scanner);

                    System.out.printf("Nuvarande MAX HP är %d, vad blir nya MAX HP \n", pokemonArray.get(i).getMaxHP());
                    System.out.print("----(1-500)----: ");
                    int updateMaxHP = ErrorHandelingService.checkIntMinMax(scanner,1, 500);

                    System.out.printf("Nuvarande typ är '%s' vad blir nya nya 'typ': ", pokemonArray.get(i).getType());
                    Type updatedType = ErrorHandelingService.checkType(scanner);

                    pokemonArray.get(i).setName(updatedName);
                    pokemonArray.get(i).setMaxHP(updateMaxHP);
                    pokemonArray.get(i).setType(updatedType);


                    System.out.println("Vill du uppdatera attackerna?");
                    System.out.print("Y/N? (N = behåller tidigare attacker): ");
                    String userChoice = ErrorHandelingService.checkNameLettersOnly(scanner);
                    if(userChoice.equalsIgnoreCase("y")){
                        for(int y = 0; y < pokemonArray.get(i).getAttacks().size(); y++ ){
                            Graphics.getEmptySpace(20);
                            Graphics.getUpdatedAttackGraphics();
                            String numberToText = "";
                            switch (y){
                                case 0 -> numberToText = "första";
                                case 1 -> numberToText = "andra";
                                case 2 -> numberToText = "tredje";
                                case 3 -> numberToText = "fjärde";
                            }
                            System.out.printf("----Updatering av din %s attack----\n", numberToText);

                            System.out.printf("Din nuvarande attack heter %s, vad vill du döpa om den till: ",
                                    pokemonArray.get(i).getAttacks().get(y).getAttackName());
                            String updatedAttackName = ErrorHandelingService.checkNameLettersOnly(scanner);

                            System.out.printf("Din nuvarande bas skada är %d, vad vill du uppdatera den till: \n",
                                    pokemonArray.get(i).getAttacks().get(y).getBaseDamage());
                            System.out.print("----(1-40)----: ");
                            int updatedBaseDamage = ErrorHandelingService.checkIntMinMax(scanner,1,40);

                            System.out.printf("Din nuvarande träffsäkerhet är %d, vad vill du uppdatera den till: \n",
                                    pokemonArray.get(i).getAttacks().get(y).getAccuracy());
                            System.out.print("----(1-100)----: ");
                            int updatedAccuracy = ErrorHandelingService.checkIntMinMax(scanner,1,100);

                            System.out.printf("Din nuvarande attack typ är %s, vad vill du uppdatera den till: ",
                                    pokemonArray.get(i).getAttacks().get(y).getType());
                            Type updatedAttackType = ErrorHandelingService.checkType(scanner);

                            pokemonArray.get(i).getAttacks().get(y).setAttackName(updatedAttackName);
                            pokemonArray.get(i).getAttacks().get(y).setBaseDamage(updatedBaseDamage);
                            pokemonArray.get(i).getAttacks().get(y).setAccuracy(updatedAccuracy);
                            pokemonArray.get(i).getAttacks().get(y).setType(updatedType);


                        }
                    }else {
                        System.out.println("behåller gamla attacker");
                    }
                }
            }
        }

    }

}
