import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> pokemonArray = FileHandler.loadPokemons();
        PokedexServices.seedPokemon(pokemonArray);
        GraphicsSelector graphics = new Graphics();
        PokedexServices pokedexService = new PokedexServices();

        boolean isRunning = true;
        while (isRunning){
            graphics.getPokedexIntro("Välkommen till din PokeIndex");
            System.out.println("Välj en av följande: ");
            System.out.println("1) Visa alla Pokemon");
            System.out.println("2) Lägg till en Pokemon");
            System.out.println("3) Ta bort en Pokemon");
            System.out.println("4) Uppdatera en pokemon");
            System.out.println("5) Spara data");
            System.out.println("6) Ladda data");
            System.out.println("7) Radera nuvarande Pokemon och ladda in seed data");
            System.out.println("8) Spara och avsluta spelet");
            System.out.println("9) Spela Pokemon-Battler");
            int userChoice = ValidationService.checkIntMinMax(scanner,1,9);

            switch (userChoice) {
                case 1 -> {
                    graphics.getEmptySpace(40);
                    graphics.getMessage("Här är alla dina Pokemon");
                    pokedexService.showAllPokemon(pokemonArray,"all");
                    ValidationService.getReturnInMenu(scanner);
                    graphics.getEmptySpace(40);
                }
                case 2 -> {
                    graphics.getEmptySpace(40);
                    pokedexService.addPokemon(pokemonArray, scanner);
                }
                case 3 -> {
                    graphics.getEmptySpace(40);
                    graphics.getMessage("Ta bort Pokemon");
                    pokedexService.removePokemon(scanner, pokemonArray);
                }
                case 4 -> {
                    graphics.getEmptySpace(40);
                    graphics.getMessage("Uppdatera Pokemon");
                    pokedexService.updatePokemon(scanner,pokemonArray);
                }
                case 5 -> {
                    graphics.getEmptySpace(40);
                    FileHandler.savePokemons(pokemonArray);
                    graphics.getMessage("Spara Pokemon");
                    ValidationService.getReturnInMenu(scanner);
                }
                case 6 -> {
                    graphics.getEmptySpace(40);
                    pokemonArray = FileHandler.loadPokemons();
                    graphics.getMessage("Laddar från tidigare fil");
                    ValidationService.getReturnInMenu(scanner);
                }
                case 7 -> {
                    graphics.getEmptySpace(40);
                    pokemonArray.clear();
                    PokedexServices.seedPokemon(pokemonArray);
                    graphics.getMessage("Dina Pokemon är borttagna & ersatt med seed Pokemon");
                    ValidationService.getReturnInMenu(scanner);
                }
                case 8 -> {
                    graphics.getEmptySpace(40);
                    graphics.getMessage("Tack för att du spelade!");
                    FileHandler.savePokemons(pokemonArray);
                    isRunning = false;
                }
                case 9 ->{
                    BattleService.StartBattle(pokemonArray,scanner);
                }
            }

        }
        scanner.close();
    }
}
