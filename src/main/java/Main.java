import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> pokemonArray = FileHandler.loadPokemons();
        Graphics.getIntroGraphics();
        boolean isRunning = true;
        while (isRunning){
            Graphics.getUserOptionsGraphics();
            int userChoice = scanner.nextInt();
            scanner.nextLine();
            switch (userChoice) {
                case 1 -> {
                    Graphics.getPokemonListGraphics();
                    pokedexServices.showAllPokemons(pokemonArray);
                    Graphics.getEndRowGraphics();
                }
                case 2 -> {
                    pokedexServices.addPokemon(pokemonArray, scanner);
                }
                case 3 -> {
                    System.out.println("Visar en lista lista på alla pokemons");
                    System.out.println("Använder väljer pokemons, skriver vilka siffra som ska bort");
                    pokedexServices.removePokemon();
                }
                case 4 -> {
                    System.out.println("Uppdatera en pokemon");
                    pokedexServices.updatePokemon();
                }
                case 5 -> {
                    System.out.println("Raderar alla pokemons och ersätter med seed data, dvs 5st pokemons");
                }
                case 6 -> {
                    System.out.println("Sparar spelet och avslutar");
                    isRunning = false;
                }
                default -> {
                    System.out.println("Obs välj mellan 1-6");
                }

            }

        };
    };
}
