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
                ==================  Välkommen till din PokeIndex =====================
                ======================================================================
                """);
        }
        public static void getPokemonListGraphics(){
            System.out.println("""
                ======================================================================
                ===================== Här är alla dina Pokemon =======================
                ======================================================================
                """);
        }
        public static void getAddPokemonGraphics(){
            System.out.println("""
                ======================================================================
                ================ Lägg till Pokemon i din Pokeindex ===================
                ======================================================================
                """);
        }
        public static void getRemovePokemonGraphics(){
            System.out.println("""
                ======================================================================
                =============== Ta bort Pokemon i från din PokeIndex =================
                ======================================================================
                """);
        }
        public static void getUpdatePokemonGraphics(){
            System.out.println("""
                ======================================================================
                ============= Updatera dina Pokemon i din PokeIndex ==================
                ======================================================================
                """);
        }
        public static void getSavedPokemon(){
        System.out.println("""
                ======================================================================
                ==================  Dina Pokemon är nu sparade  ======================
                ======================================================================
                """);
        }
        public static void getResetToSeedGraphics(){
            System.out.println("""
                ======================================================================
                ================ Alla dina Pokemon är nu borttagna ===================
                =================== Och ersatt med seed Pokemon ======================
                ======================================================================
                """);
        }
        public static void getAttackRemoveGraphics(){
            System.out.println("""
                    ======================================================================
                    ======================= Ta bort attack ===============================
                    ======================================================================
                    """);
        }
        public static void getQuitGameGraphics(){
            System.out.println("""
                ======================================================================
                ==================== Tack för att du spelade =========================
                ======================================================================
                """);
        }
        public static void getLoadGraphics(){
            System.out.println("""
                ======================================================================
                ==================== Laddar från tidigare fil ========================
                ======================================================================
                """);
        }
        public static void getUserOptionsGraphics(){
            System.out.println("Välj en av följande: ");
            System.out.println("1) Visa alla Pokemon");
            System.out.println("2) Lägg till en Pokemon");
            System.out.println("3) Ta bort en Pokemon");
            System.out.println("4) Uppdatera en pokemon");
            System.out.println("5) Spara data");
            System.out.println("6) Ladda data");
            System.out.println("7) Radera nuvarande Pokemon och ladda in seed data");
            System.out.println("8) Spara och avsluta spelet");
        }
        public static void getAttackGraphics(){
            System.out.println("""
                ======================================================================
                =======================  Lägg till Attack  ===========================
                ======================================================================
                """);
        }
        public static void getUpdatedAttackGraphics(){
            System.out.println("""
                ======================================================================
                ====================  Uppdatera din Pokemon  =========================
                ======================================================================
                """);
        }
        public static void getPokemonCreatedGraphics(){
            System.out.println("""
                ======================================================================
                ==================  Din Pokemon är nu skapad =========================
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
