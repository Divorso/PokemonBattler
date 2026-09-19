import java.io.IOException;
import java.util.ArrayList;

public class pokedexServices {
    public static void showAllPokemons() throws IOException{
        System.out.println("Shows all pokemon");
        ArrayList<Pokemon> pokemons = FileHandler.loadPokemons();
        System.out.println("Antal Pokemons: " + pokemons.size());
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
