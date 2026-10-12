public class DamageCalculator {
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
