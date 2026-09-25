import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> pokemonArray = FileHandler.loadPokemons();
        System.out.println(pokemonArray.get(0).getName());
        pokedexServices.seedPokemon(pokemonArray);

        boolean isRunning = true;
        while (isRunning){
            Graphics.getIntroGraphics();
            Graphics.getUserOptionsGraphics();
            int userChoice = ErrorHandelingService.checkIntMinMax(scanner,1,8);
            switch (userChoice) {
                case 1 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getPokemonListGraphics();
                    pokedexServices.showAllPokemons(pokemonArray,"all");
                    Graphics.getReturnInMenu(scanner);
                    Graphics.getEmptySpace(20);
                }
                case 2 -> {
                    Graphics.getEmptySpace(20);
                    pokedexServices.addPokemon(pokemonArray, scanner);
                }
                case 3 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getRemovePokemonGrahpics();
                    pokedexServices.removePokemon(scanner, pokemonArray);
                }
                case 4 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getUpdatePokemonGraphics();
                    pokedexServices.updatePokemon(scanner,pokemonArray);
                }
                case 5 -> {
                    Graphics.getEmptySpace(20);
                    FileHandler.savePokemons(pokemonArray);
                    Graphics.getSavedPokemon();
                    Graphics.getReturnInMenu(scanner);
                }
                case 6 -> {
                    Graphics.getEmptySpace(20);
                    pokemonArray = FileHandler.loadPokemons();
                    Graphics.getLoadGraphics();
                    Graphics.getReturnInMenu(scanner);
                }
                case 7 -> {
                    Graphics.getEmptySpace(20);
                    pokemonArray.clear();
                    pokedexServices.seedPokemon(pokemonArray);
                    Graphics.getResetToSeedGraphics();
                    Graphics.getReturnInMenu(scanner);
                }
                case 8 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getQuitGameGraphics();
                    isRunning = false;
                }
            }

        };
    };
}
