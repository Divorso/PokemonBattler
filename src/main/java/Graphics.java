import java.util.Scanner;

public class Graphics {
        public static void getIntroGraphics() {
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
               
            
                ======================================================================
                ====================  Welcome to the PokeIndex. ======================
                ======================================================================
                """);
        }
        public static void getPokemonListGraphics(){
            System.out.println("""
                ======================================================================
                ================== Here are all you your pokemons ====================
                ======================================================================
                """);
        }
        public static void getAddPokemonGraphics(){
            System.out.println("""
                ======================================================================
                ==================== Add Pokemon to your index =======================
                ======================================================================
                """);
        }
        public static void  getRemovePokemonGrahpics(){
            System.out.println("""
                ======================================================================
                ================= Remove a Pokemon from your index ===================
                ======================================================================
                """);
        }
        public static void getUpdatePokemonGraphics(){
            System.out.println("""
                ======================================================================
                ================= Update a Pokemon in your index =====================
                ======================================================================
                """);
        }
        public static void getResetToSeedGraphics(){
            System.out.println("""
                ======================================================================
                ================= All Pokemon have been removed ======================
                ================= Your index have been resetted ======================
                ======================  To it's seed data  ===========================
                ======================================================================
                """);
        }
        public static void getQuitGameGraphics(){
            System.out.println("""
                ======================================================================
                ===================== Game has been saved ============================
                ==================== Thank you for playing ===========================
                ======================================================================
                """);
        }
        public static void getEndRowGraphics(){
            System.out.println("""
                ======================================================================
                ======================================================================
                ======================================================================
                """);
        }
        public static void getUserOptionsGraphics(){
            System.out.println("Välj en av följande: ");
            System.out.println("1) Visa alla Pokemon");
            System.out.println("2) Lägg till en Pokemon");
            System.out.println("3) Ta bort en Pokemon");
            System.out.println("4) Uppdatera en pokemon");
            System.out.println("5) Ladda från fil");
            System.out.println("7) Spara till fil");
            System.out.println("8) Radera alla Pokemon och gå tillbaka till seed data");
            System.out.println("9) Avsluta spelet");
        }
        public static void getAttackGraphics(){
            System.out.println("""
                ======================================================================
                ==================  Add attack to your Pokemon  ======================
                ======================================================================
                """);
        }
        public static void getPokemonCreatedGraphics(){
            System.out.println("""
                ======================================================================
                ==================  Your Pokemon have been created  ==================
                ======================================================================
                    """);
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
        public static void getEmptySpace(int number){
            for(int i = 0; i < number; i++){
                System.out.println(" ");
            }

        }
}
