import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Graphics.getIntroGraphics();
        boolean isRunning = true;
        while (isRunning){
            Graphics.getUserOptionsGraphics();
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> {
                    pokedexServices.showAllPokemons();
                }
                case 2 -> {
                    System.out.println("Visar alla värdem som måste fyllas in för att skapa en ny pokemon");
                    pokedexServices.addPokemon();
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
