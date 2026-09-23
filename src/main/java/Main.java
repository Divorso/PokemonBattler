import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> pokemonArray = FileHandler.loadPokemons();
        System.out.println(pokemonArray.getFirst());
        pokemonArray.contains("Charmander");
        boolean isRunning = true;
        while (isRunning){
            Graphics.getIntroGraphics();
            Graphics.getUserOptionsGraphics();
            int userChoice = scanner.nextInt();
            scanner.nextLine();
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
                    System.out.println("Raderar alla pokemons och ersätter med seed data, dvs 5st pokemons");
                }
                case 6 -> {
                    System.out.println("Spara till fil");
                }
                case 7 -> {
                    System.out.println("Ladda från fil");
                }
                case 8 -> {
                    System.out.println("Sparar spelet och avslutar");
                    //isRunning = false;
                }
                default -> {
                    System.out.println("Obs välj mellan 1-6");
                }

            }

        };
    };
}
