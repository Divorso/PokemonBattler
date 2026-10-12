import java.util.ArrayList;
import java.util.Scanner;

public class BattleService {
    public static void StartBattle(ArrayList<Pokemon> pokemonArray, Scanner scanner){
        if(pokemonArray.isEmpty()){
            System.out.println("Du har inga Pokemon.");
            return;
        }
        ArrayList<Pokemon> cpuPokemonList = new ArrayList<>();
        PokedexServices.seedPokemon(cpuPokemonList);
        int randomIndex = (int) (Math.random() * cpuPokemonList.size());
        Pokemon cpu = cpuPokemonList.get(randomIndex);
        Pokemon playerPokemon = null;


        PokedexServices.showAllPokemon(pokemonArray,"name");
        System.out.println("Välj en Pokemon");
        String choicePokemon = scanner.nextLine();

        for(Pokemon pokemon : pokemonArray){
            if(pokemon.getName().equalsIgnoreCase(choicePokemon)){
                playerPokemon = pokemon;
                break;
            }
        }
        System.out.println("Du kommer möta CPU pokemon: " + cpu.getName());
        System.out.println("Du har valt: " + playerPokemon.getName());

        System.out.println("Redo att starta striden?: y/n");



    }

}
