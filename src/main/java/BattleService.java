import java.util.ArrayList;
import java.util.Scanner;

public class BattleService {
    public static void StartBattle(ArrayList<Pokemon> pokemonArray, Scanner scanner) {
        if (pokemonArray.isEmpty()) {
            System.out.println("Du har inga Pokemon.");
            return;
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

            if(playerFirst ){
                Attack playerAttack = chooseAttack(playerPokemon,scanner, "player");
                performAttack(playerPokemon,cpuPokemon,playerAttack);
                Attack cpuAttack = chooseAttack(cpuPokemon,scanner,"CPU");
                performAttack(cpuPokemon,playerPokemon,cpuAttack);
            } else {
                Attack cpuAttack = chooseAttack(cpuPokemon,scanner,"CPU");
                performAttack(cpuPokemon,playerPokemon,cpuAttack);
                Attack playerAttack = chooseAttack(playerPokemon,scanner, "player");
                performAttack(playerPokemon,cpuPokemon,playerAttack);
            }
            round++;

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
    public static void performAttack(Pokemon attacker, Pokemon defender, Attack attack) {
        int hitRoll = (int) (Math.random() * 100) + 1;

        System.out.printf("%s använder attacken %s\n", attacker.getName(), attack.getAttackName());

        if (hitRoll > attack.getAccuracy()) {
            System.out.println("Attacken missade!");
            return;
        }

        double damageEffectivenessMultiplier = Math.random();
        double damage = attack.getBaseDamage() * damageEffectivenessMultiplier;
        int damageSent = (int)damage;

        defender.takeDamage(damageSent);

        System.out.println("Attacken träffade och gjorde " + damageSent + " skada!");
        System.out.println(defender.getName() + " har " + defender.getCurrentHP() + " HP kvar.");
    }
}
