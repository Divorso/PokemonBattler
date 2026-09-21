import java.util.ArrayList;

public class pokedexServices {
    public static void showAllPokemons(ArrayList<Pokemon> pokemonArray){
        for(Pokemon pokemon : pokemonArray){
            System.out.println(pokemon.getName());
        };
    }
    public static void addPokemon(){
        Graphics.getAddPokemonGraphics();


    }
    public static void removePokemon(){
        System.out.println("removes pokemon");
    }
    public static void updatePokemon(){
        System.out.println("update pokemon");
    }

}
