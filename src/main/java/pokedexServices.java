import java.io.IOException;
import java.util.ArrayList;

public class pokedexServices {

    public static void showAllPokemons() throws IOException{
        ArrayList<Pokemon> pokemons = FileHandler.loadPokemons();;
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("_________________Dina pokemons:___________________");
        for(Pokemon pokemon : pokemons){
            System.out.println(pokemon.getName());
        };
        System.out.println("__________________________________________________");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("**************************************************");


    }
    public static void addPokemon(){
        System.out.println("Adds pokemons");

    }
    public static void removePokemon(){
        System.out.println("removes pokemon");
    }
    public static void updatePokemon(){
        System.out.println("update pokemon");
    }

}
