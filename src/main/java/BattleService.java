import java.util.ArrayList;
import java.util.Scanner;

public class BattleService {
    public static void StartBattle(ArrayList<Pokemon> pokemonArray, Scanner scanner) {
        BattleStatistics statistics = new BattleStatistics(0,0);
        boolean isRunning = true;

        while (isRunning) {

            if (pokemonArray.isEmpty()) {
                System.out.println("Du har inga Pokemon.");
                isRunning = false;
                break;
            }

            ArrayList<Pokemon> cpuPokemonList = new ArrayList<>();
            PokedexServices.seedPokemon(cpuPokemonList);

            int randomIndex = (int) (Math.random() * cpuPokemonList.size());
            Pokemon cpuPokemon = cpuPokemonList.get(randomIndex);
            Pokemon playerPokemon = null;


            PokedexServices.showAllPokemon(pokemonArray, "name");
            System.out.println("Välj en Pokemon");
            String choicePokemon = ErrorHandelingService.checkForPokemon(scanner, pokemonArray);


            if (choicePokemon.equalsIgnoreCase("b")) {
                return;
            }

            for (Pokemon pokemon : pokemonArray) {
                if (pokemon.getName().equalsIgnoreCase(choicePokemon)) {
                    playerPokemon = pokemon;
                    break;
                }
            }


            System.out.println("Du kommer möta CPU pokemon: " + cpuPokemon.getName());
            System.out.println("Du har valt: " + playerPokemon.getName());
            System.out.println("Högst roll börjar striden");

            playerPokemon.resetHP();
            cpuPokemon.resetHP();

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
                    Attack playerAttack = chooseAttack(playerPokemon, scanner, "player");
                    DamageCalculator.performAttack(playerPokemon, cpuPokemon, playerAttack);
                    if(!cpuPokemon.isDefeated()){
                        Attack cpuAttack = chooseAttack(cpuPokemon, scanner, "CPU");
                        DamageCalculator.performAttack(cpuPokemon, playerPokemon, cpuAttack);
                    }
                } else {
                    Attack cpuAttack = chooseAttack(cpuPokemon, scanner, "CPU");
                    DamageCalculator.performAttack(cpuPokemon, playerPokemon, cpuAttack);
                    if(!playerPokemon.isDefeated()){
                        Attack playerAttack = chooseAttack(playerPokemon, scanner, "player");
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
                isRunning = false;
            }


        }
    }
    public static Attack chooseAttack(Pokemon pokemon, Scanner scanner, String attackerSelector) {

        if(attackerSelector.equalsIgnoreCase("player")){
            ArrayList<Attack> attacks = pokemon.getAttacks();

            for (int i = 0; i < attacks.size(); i++) {
                Attack attack = attacks.get(i);

                System.out.printf("#%d -- Attack: %s, Bas skada: %d, Träffsäkerhet: %d%%%n",
                        i + 1,
                        attack.getAttackName(),
                        attack.getBaseDamage(),
                        attack.getAccuracy());
            }

            while (true) {
                System.out.print("Välj en attack: ");
                String input = scanner.nextLine();

                try {
                    int choice = Integer.parseInt(input);

                    if (choice >= 1 && choice <= attacks.size()) {
                        return attacks.get(choice - 1);
                    }

                    System.out.println("Ogiltigt attacknummer.");

                } catch (NumberFormatException e) {
                    System.out.println("Du måste ange en siffra.");
                }
            }
        } else if (attackerSelector.equalsIgnoreCase("CPU")) {
            ArrayList<Attack> attacks = pokemon.getAttacks();
            int randomIndex = (int) (Math.random() * attacks.size());
            Attack cpuAttackRandom = attacks.get(randomIndex);

            return cpuAttackRandom;

        }
        throw new IllegalArgumentException("Ogiltig attackerSelector: " + attackerSelector);
    }
}
