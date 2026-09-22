import java.util.ArrayList;
import java.util.Scanner;

public class pokedexServices {
    public static void showAllPokemons(ArrayList<Pokemon> pokemonArray){
        for(Pokemon pokemon : pokemonArray){

            System.out.printf("Namn: %s, max HP: %d, nuvarande HP %d, Pokemon typ: %s \n",
                    pokemon.getName(),pokemon.getMaxHP(),pokemon.getCurrentHP(),pokemon.getType());
        };
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

        System.out.println("----Hur många attacker har din Pokemon?----");
        System.out.print("----(Du får ha mellan 1-4 attacker)----: ");
        int amountAttacks = ErrorHandelingService.checkIntMinMax(scanner,1,4 );
        for(int i = 0; i < amountAttacks; i++ ){
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

            //lägger till ny pokemon
            Pokemon newPokemon = new Pokemon();
            newPokemon.setName(name);
            newPokemon.setMaxHP(maxHP);
            newPokemon.setCurrentHP(maxHP);
            newPokemon.setType(type);
            //lägger till ny attack
            ArrayList<Attack> attackArray = new ArrayList<>();
            Attack newAttack = new Attack();
            newAttack.setAttackName(attackName);
            newAttack.setBaseDamage(attackDamage);
            newAttack.setAccuracy(accuracy);
            newAttack.setType(typeAttack);
            attackArray.add(newAttack);
            newPokemon.setAttacks(attackArray);

            pokemonArray.add(newPokemon);
        }
        Graphics.getPokemonCreatedGraphics();
    }
    public static void removePokemon(){
        System.out.println("removes pokemon");
    }
    public static void updatePokemon(){
        System.out.println("update pokemon");
    }

}
