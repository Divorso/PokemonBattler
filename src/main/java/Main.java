import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> pokemonArray = FileHandler.loadPokemons();
        PokedexServices.seedPokemon(pokemonArray);

        boolean isRunning = true;
        while (isRunning){
            Graphics.getIntroGraphics();
            Graphics.getUserOptionsGraphics();
            int userChoice = ErrorHandelingService.checkIntMinMax(scanner,1,8);
            switch (userChoice) {
                case 1 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getPokemonListGraphics();
                    PokedexServices.showAllPokemon(pokemonArray,"all");
                    Graphics.getReturnInMenu(scanner);
                    Graphics.getEmptySpace(20);
                }
                case 2 -> {
                    Graphics.getEmptySpace(20);
                    PokedexServices.addPokemon(pokemonArray, scanner);
                }
                case 3 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getRemovePokemonGraphics();
                    PokedexServices.removePokemon(scanner, pokemonArray);
                }
                case 4 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getUpdatePokemonGraphics();
                    PokedexServices.updatePokemon(scanner,pokemonArray);
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
                    PokedexServices.seedPokemon(pokemonArray);
                    Graphics.getResetToSeedGraphics();
                    Graphics.getReturnInMenu(scanner);
                }
                case 8 -> {
                    Graphics.getEmptySpace(20);
                    Graphics.getQuitGameGraphics();
                    FileHandler.savePokemons(pokemonArray);
                    isRunning = false;
                }
            }

        }
        scanner.close();
    }
}
