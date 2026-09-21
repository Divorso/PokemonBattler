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
            System.out.println("1) Visa alla Pokemons");
            System.out.println("2) Lägg till en Pokemon");
            System.out.println("3) Ta bort en Pokemon");
            System.out.println("4) Uppdatera en pokemon");
            System.out.println("5) Radera alla Pokemon och gå tillbaka till seed data");
            System.out.println("6) Spara och avsluta spelet");
        }
}
