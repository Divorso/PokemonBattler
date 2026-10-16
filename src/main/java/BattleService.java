import java.util.ArrayList;
import java.util.Scanner;

public class BattleService {
    public static void StartBattle(ArrayList<Pokemon> pokemonArray, Scanner scanner) {
        PokedexServices pokedexServices = new PokedexServices();
        GraphicsSelector graphics = new Graphics();
        BattleStatistics statistics = FileHandler.loadBattleStatistics();
        AttackSelector playerSelector = new PlayerAttackSelector(scanner);
        AttackSelector cpuSelector = new CPUAttackSelector();

        graphics.getEmptySpace(80);
        graphics.getBattleIntro("Välkommen till din Pokemon Battler");
        statistics.showStatistics();
        System.out.println("=".repeat(80));
        statistics.showMostUsed();
        System.out.println("=".repeat(80));
        while (true) {
            if (pokemonArray.isEmpty()) {
                System.out.println("Du har inga Pokemon.");
                return;
            }
            ArrayList<Pokemon> cpuPokemonList = new ArrayList<>();
            PokedexServices.seedPokemon(cpuPokemonList);


            int randomIndex = (int) (Math.random() * cpuPokemonList.size());
            Pokemon cpuPokemon = cpuPokemonList.get(randomIndex);
            Pokemon playerPokemon = null;


            pokedexServices.showAllPokemon(pokemonArray, "battle");
            System.out.println("""
                            
                            Välj en Pokemon
                            Eller skriv 'b' för att backa""");
            String choicePokemon = ValidationService.checkForPokemon(scanner, pokemonArray);
            if (choicePokemon.equalsIgnoreCase("b")) return;

            for (Pokemon pokemon : pokemonArray) {
                if (pokemon.getName().equalsIgnoreCase(choicePokemon)) {
                    playerPokemon = pokemon;
                    break;
                }
            }


            if (playerPokemon.getAttacks() == null || playerPokemon.getAttacks().isEmpty()) {
                System.out.println("Din Pokémon har inga attacker och kan inte strida.");
                continue;
            }
            if (cpuPokemon.getAttacks() == null || cpuPokemon.getAttacks().isEmpty()) {
                System.out.println("Motståndarens Pokémon har inga attacker.");
                continue;
            }
            if (playerPokemon.isDefeated()) {
                graphics.getEmptySpace(80);
                System.out.println("=".repeat(80));
                System.out.printf("%s har svimmat och kan inte strida.\n", playerPokemon.getName());
                System.out.println("=".repeat(80));
                continue;
            }

            graphics.getEmptySpace(50);
            graphics.getMessage(cpuPokemon.getName() + " VS " + playerPokemon.getName());
            System.out.println("CPU: " + cpuPokemon.getName());
            System.out.println("Spelare: " + playerPokemon.getName());
            System.out.println("=".repeat(80));



            System.out.println("=".repeat(80));
            System.out.println("Skriv 'y' för att starta striden.");
            String userChoice = scanner.nextLine();
            if (!userChoice.equalsIgnoreCase("y")) {
                System.out.println("Spelet är avbryten");
                continue;
            }
            statistics.addPokemonUsage(playerPokemon.getName());

            int round = 1;
            graphics.getEmptySpace(50);
            boolean starter = whoWillStart(graphics);
            Pokemon firstPokemon = starter ? playerPokemon : cpuPokemon;
            Pokemon secondPokemon = starter ? cpuPokemon : playerPokemon;

            AttackSelector firstSelector = starter ? playerSelector : cpuSelector;
            AttackSelector secondSelector = starter ? cpuSelector : playerSelector;
            while (!playerPokemon.isDefeated() && !cpuPokemon.isDefeated()) {
                graphics.getMessage("Runda: " + round);
                round++;
                System.out.printf("Player HP: %d\n", playerPokemon.getCurrentHP());
                System.out.printf("CPU HP: %d\n", cpuPokemon.getCurrentHP());
                System.out.println("=".repeat(80));

                executeTurn(firstPokemon, secondPokemon, firstSelector,
                        statistics, firstPokemon == playerPokemon);

                if (!secondPokemon.isDefeated()) {
                    executeTurn(secondPokemon, firstPokemon, secondSelector,
                            statistics, secondPokemon == playerPokemon);
                }
                System.out.println("#".repeat(80));
                System.out.println("#".repeat(80));
            }
            if (cpuPokemon.isDefeated()) {
                System.out.println("Du vann!");
                statistics.addPlayerWin(playerPokemon.getName());

            } else if (playerPokemon.isDefeated()) {
                System.out.println("Du förlorade!");
                statistics.addCpuWin(playerPokemon.getName());
            }
            statistics.showStatistics();
            FileHandler.saveBattleStatistics(statistics);
            statistics.showMostUsed();
            System.out.print("""
                    Spela vidare?
                    1) - Forsätt
                    2) - Avslut
                    val: """);
            int keepPlaying = ValidationService.checkIntMinMax(scanner,1,2);
            System.out.println("");
            if (keepPlaying == 2) {
                System.out.println("Tack för att du spelade! ");
                break;
            }
        }
    }
    private static boolean whoWillStart(GraphicsSelector graphics) {
        graphics.getMessage("Högst tärningsslag börjar striden");

        int playerRoll;
        int cpuRoll;

        do {
            graphics.getLoadingTimer(6, "Kastar tärning ->");

            playerRoll = (int) (Math.random() * 6) + 1;
            cpuRoll = (int) (Math.random() * 6) + 1;

            System.out.println("\nPlayer kastade: " + playerRoll);
            System.out.println("CPU kastade: " + cpuRoll);

            if (playerRoll == cpuRoll) {
                System.out.println("Oavgjort! Vi kastar igen.");
            }

        } while (playerRoll == cpuRoll);

        boolean playerFirst = playerRoll > cpuRoll;

        if (playerFirst) {
            System.out.println("Player börjar!");
        } else {
            System.out.println("CPU börjar!");
        }

        return playerFirst;
    }
    private static void executeTurn(Pokemon attacker, Pokemon defender,
                                    AttackSelector selector,
                                    BattleStatistics statistics,
                                    boolean isPlayer) {

        Attack attack = selector.chooseAttack(attacker);

        if (isPlayer) {
            statistics.addAttackUsage(attack.getAttackName());
        }

        DamageCalculator.performAttack(attacker, defender, attack);
    }
}
