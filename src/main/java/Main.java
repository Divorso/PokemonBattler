import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                                                  ,'\\
                    _.----.        ____         ,'  _\\   ___    ___     ____
                _,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.
                \\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |
                 \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |
                   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |
                    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |
                     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |
                      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |
                       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |
                        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |
                                                `'                            '-._|
                """);
        System.out.println("""
                =================================================================
                ===================Welcome to the PokeIndex======================
                =================================================================""");




        boolean isRunning = true;
        while (isRunning){
            System.out.println("Välj en av följande: ");
            System.out.println("1) Visa alla Pokemons");
            System.out.println("2) Lägg till en Pokemon");
            System.out.println("3) Ta bort en Pokemon");
            System.out.println("4) Uppdatera en pokemon");
            System.out.println("5) Radera alla Pokemon och gå tillbaka till seed data");
            System.out.println("6) Spara och avsluta spelet");
            int userChoice = scanner.nextInt();


            switch (userChoice) {
                case 1 -> {
                    System.out.println("Visar alla pokemons");
                    //showAllPokemons.readPokemons();
                    //FileHandler.readFile();
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
