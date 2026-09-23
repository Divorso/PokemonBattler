import java.util.ArrayList;
import java.util.Scanner;

public class ErrorHandelingService {
    public static int checkIntMinMax(Scanner scanner, int min, int max){
        while(true){
            try{
                int userInput = Integer.parseInt(scanner.nextLine().trim());
                if(userInput >= min && userInput <= max){
                    return userInput;
                }
                System.out.printf("Fel, värdet måste vara mellan %d och %d: ", min, max);
            } catch (NumberFormatException e){
                System.out.printf("Fel, värdet måste vara ett nummer mellan (%d och %d): ",min, max);
            }
        }
    }
    public static String checkNameLettersOnly(Scanner scanner){
        while(true){
            String userInput = scanner.nextLine().trim();
            if(userInput.matches("[a-zA-Z]+")){
                    return userInput;
            }
            System.out.print("Fel använd endast bokstäver A-Z: ");
        }
    }
    public static Type checkType(Scanner scanner) {
        while(true){
            try {
                String userInput = scanner.nextLine().trim().toUpperCase();
                try {
                    return Type.valueOf(userInput);
                } catch (IllegalArgumentException e){
                    throw new InvalidTypeException(
                            "Ogiltigt Pokemon-typ."
                    );
                }
            }catch (InvalidTypeException e){
                System.out.println(e.getMessage());
                System.out.print("Välj mellan PHYSICAL, FIRE, ICE, WATER, ELECTRIC ELLER GRASS: ");
            }
        }
    }
    public static String checkForPokemon(Scanner scanner, ArrayList<Pokemon> pokemonArray) {

        while (true) {
            String userInput = checkNameLettersOnly(scanner).trim();
            try {
                if(userInput.equalsIgnoreCase("b")){
                    return userInput;
                }

                for (Pokemon pokemon : pokemonArray) {

                    if (pokemon.getName().equalsIgnoreCase(userInput)) {
                        return userInput;
                    }
                }
                throw new InvalidPokemonException();

            } catch (InvalidPokemonException e) {
                System.out.printf("Hittar ingen Pokemon som heter %s, testa igen eller skriv 'b' för att backa: ", userInput);
            }
        }
    }
}