import java.util.ArrayList;
import java.util.Scanner;

public class BattleService {
    public static void StartBattle(ArrayList<Pokemon> pokemonArray, Scanner scanner) {
        PokedexServices pokedexServices = new PokedexServices();
        GraphicsSelector graphics = new Graphics();
        BattleStatistics statistics = FileHandler.loadBattleStatistics();
        AttackSelector playerSelector = new PlayerAttackSelector(scanner);
        AttackSelector cpuSelector = new CPUAttackSelector();

        graphics.getEmptySpace(40);
        graphics.getLoadingTimer(6);
        graphics.getBattleIntro("Välkommen till din Pokemon Battler");
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
            System.out.println("Välj en Pokemon");
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
                System.out.printf("%s har svimmat och kan inte strida.\n",playerPokemon.getName());
                continue;
            }
            graphics.getEmptySpace(20);
            System.out.println("Du kommer möta CPU pokemon: " + cpuPokemon.getName());
            System.out.println("Du har valt: " + playerPokemon.getName());
            System.out.println("Högst roll börjar striden");

            int playerRoll = (int) (Math.random() * 100) + 1;
            int cpuRoll = (int) (Math.random() * 100) + 1;
            boolean playerFirst = playerRoll >= cpuRoll;

            System.out.println("Player rolled: " + playerRoll);
            System.out.println("CPU rolled: " + cpuRoll);
            if (playerFirst) {
                System.out.println("Player börjar!");
            } else {
                System.out.println("Cpu börjar!");
            }

            System.out.println("Redo att starta striden?: y/n");
            String userChoice = scanner.nextLine();
            if (!userChoice.equalsIgnoreCase("y")) {
                System.out.println("Spelet är avbryten");
                return;
            }

            int round = 1;

            while (!playerPokemon.isDefeated() && !cpuPokemon.isDefeated()) {
                System.out.println("Nuvarande runda: " + round);
                System.out.printf("Player HP: %d\n", playerPokemon.getCurrentHP());
                System.out.printf("CPU HP: %d\n", cpuPokemon.getCurrentHP());

                if (playerFirst) {
                    Attack playerAttack = playerSelector.chooseAttack(playerPokemon);
                    DamageCalculator.performAttack(playerPokemon, cpuPokemon, playerAttack);
                    if(!cpuPokemon.isDefeated()){
                        Attack cpuAttack = cpuSelector.chooseAttack(cpuPokemon);
                        DamageCalculator.performAttack(cpuPokemon, playerPokemon, cpuAttack);
                    }
                } else {
                    Attack cpuAttack = cpuSelector.chooseAttack(cpuPokemon);
                    DamageCalculator.performAttack(cpuPokemon, playerPokemon, cpuAttack);
                    if(!playerPokemon.isDefeated()){
                        Attack playerAttack = playerSelector.chooseAttack(playerPokemon);
                        DamageCalculator.performAttack(playerPokemon, cpuPokemon, playerAttack);
                    }

                }
                round++;
                }
                if (cpuPokemon.isDefeated()) {
                    System.out.println("Du vann!");
                    statistics.addPlayerWin();
                } else if(playerPokemon.isDefeated()) {
                    System.out.println("Du förlorade!");
                    statistics.addCpuWin();
                }
                statistics.showStatistics();
            FileHandler.saveBattleStatistics(statistics);
            System.out.println("Skriv 'avslut' för att avsluta spelet: ");
            String keepPlaying = scanner.nextLine();
            if(keepPlaying.equalsIgnoreCase("avslut")){
                System.out.println("Tack för att du spelade! ");
                break;
            }

            graphics.getEmptySpace(20);
        }
    }
}
