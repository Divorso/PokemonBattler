import java.util.ArrayList;
import java.util.Scanner;

public class pokedexServices {
    public static void showAllPokemons(ArrayList<Pokemon> pokemonArray){
        for(Pokemon pokemon : pokemonArray){
            System.out.println(pokemon.getName());
        };
    }
    public static void addPokemon(ArrayList<Pokemon> pokemonArray, Scanner scanner){
        Graphics.getAddPokemonGraphics();
        System.out.print("----Vad ska din Pokemon heta----: ");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("----Vad är maxHP på din Pokemon----: ");
        int maxHP = scanner.nextInt();
        scanner.nextLine();
        System.out.println("--Vad är din Pokemon för typ välj mellan--");
        System.out.println("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS)");
        String type = scanner.nextLine();
        scanner.nextLine();
        System.out.println("----Hur många attacker har din Pokemon?----");
        System.out.print("----(Du får ha mellan 1-4 attacker)----");
        int amountAttacks = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < amountAttacks; i++ ){
            Graphics.getAttackGraphics();
            String numberToText = "";
            switch (i){
                case 0 -> numberToText = "första";
                case 1 -> numberToText = "andra";
                case 2 -> numberToText = "tredje";
                case 3 -> numberToText = "fjärde";
            }
            System.out.printf("Vad heter din %s attack: ", numberToText);
            String attackName = scanner.nextLine();
            System.out.print("Hur mycket skada gör den (1-40): ");
            int attackDamage = scanner.nextInt();
            System.out.print("Hur träffsäker är den (1-100): ");
            int accuracy = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Vad är det för typ av Pokemon?");
            System.out.print("(PHYSICAL, FIRE, ICE, WATER, ELECTRIC, GRASS): ");
            String typeAttack = scanner.nextLine();
        }


    }
    public static void removePokemon(){
        System.out.println("removes pokemon");
    }
    public static void updatePokemon(){
        System.out.println("update pokemon");
    }

}
