import java.util.ArrayList;
import java.util.Scanner;

public class PlayerAttackSelector implements AttackSelector {

    private final Scanner scanner;

    public PlayerAttackSelector(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Attack chooseAttack(Pokemon pokemon) {
        ArrayList<Attack> attacks = pokemon.getAttacks();
        int attackIndex = 1;
        for (Attack attack : attacks) {
            System.out.printf(
                    "#%d) Attacknamn: %s, Bas skada: %d, Träffsäkerhet: %d%% %n",
                    attackIndex,
                    attack.getAttackName(),
                    attack.getBaseDamage(),
                    attack.getAccuracy()
            );
            attackIndex++;
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
    }
}