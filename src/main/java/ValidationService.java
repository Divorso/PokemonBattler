import java.util.ArrayList;
import java.util.Scanner;

public class ValidationService {
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
    public static String checkPokemonName(Scanner scanner) {
        while (true) {
            String name = checkNameLettersOnly(scanner);

            if (name.length() >= 3) {
                return name;
            }

            System.out.print("Pokemon måste ha minst 3 bokstäver. Försök igen: ");
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
                if(userInput.equalsIgnoreCase("b")){
                    return userInput;
                }
            try {
                PokedexServices.findPokemon(userInput,pokemonArray);
                return userInput;

            } catch (InvalidPokemonException e) {
                System.out.println(e.getMessage());
                System.out.println("Testa igen eller skriv 'b' för att backa");
            }
        }
    }
    public static boolean getReturnInMenu(Scanner scanner) {

        while (true) {
            System.out.print("Skriv B för att backa: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("b")) {
                return true;
            }
            System.out.println("Fel val.");
        }
    }
    public static String numberToText(int number){
        return switch (number){
            case 0 -> "första";
            case 1 -> "andra";
            case 2 -> "tredje";
            case 3 -> "fjärde";
            default -> "okänt";
        };
    }
    public static String checkAttackName(Scanner scanner) {

        while (true) {
            String attackName = scanner.nextLine().trim();

            if (attackName.length() >= 3 && attackName.matches("[a-zA-Z ]+")) {
                return attackName;
            }

            System.out.print("Attackens namn måste ha minst 3 bokstäver. Försök igen: ");
        }
    }
}