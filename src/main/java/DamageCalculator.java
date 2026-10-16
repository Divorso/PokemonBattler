public class DamageCalculator {
    public static void performAttack(Pokemon attacker, Pokemon defender, Attack attack) {
        int hitRoll = (int) (Math.random() * 100) + 1;

        System.out.printf("%s använder attacken %s\n", attacker.getName(), attack.getAttackName());

        if (hitRoll > attack.getAccuracy()) {
            System.out.println("Attacken missade!");
            return;
        }

        double randomMultiplier = 0.5 + Math.random() * 0.5;
        double typeMultiplier = getTypeMultiplier(attack.getType(), defender.getType());
        boolean criticalHit = (int) (Math.random() * 10) == 0;
        double criticalMultiplier = criticalHit ? 2.0 : 1.0;

        double damage = attack.getBaseDamage() * randomMultiplier * typeMultiplier * criticalMultiplier;
        if (criticalHit) {
            System.out.println("CRITICAL HIT! 2x skada!!");
        }

        if (typeMultiplier == 2) {
            System.out.println("Effektiv skada!!");
        } else if (typeMultiplier == 0.5) {
            System.out.println("Skadan var mindre effektiv.");
        }

        int damageSent = Math.max(1, (int) Math.round(damage));

        defender.takeDamage(damageSent);

        System.out.println("Attacken träffade och gjorde " + damageSent + " skada!");
        System.out.println(defender.getName() + " har " + defender.getCurrentHP() + " HP kvar.");
    }
    public static double getTypeMultiplier(Type attackType, Type defenderType) {

        switch (attackType){
            case PHYSICAL -> {
                return 1;
            }
            case FIRE -> {
                if(defenderType == Type.GRASS || defenderType == Type.ICE){
                    return 2;
                } else if (defenderType == Type.WATER || defenderType == Type.ELECTRIC) {
                    return 0.5;
                } else {
                    return 1;
                }
            }
            case ICE -> {
                if(defenderType == Type.GRASS || defenderType == Type.WATER){
                    return 2;
                } else if (defenderType == Type.FIRE || defenderType == Type.ELECTRIC) {
                    return 0.5;
                } else {
                    return 1;
                }
            }
            case WATER -> {
                if(defenderType == Type.ELECTRIC || defenderType == Type.FIRE ){
                    return 2;
                } else if (defenderType == Type.GRASS || defenderType == Type.ICE) {
                    return 0.5;
                }else {
                    return 1;
                }
            }
            case ELECTRIC -> {
                if(defenderType == Type.WATER){
                    return 2;
                } else{
                    return 1;
                }
            }
            case GRASS -> {
                if(defenderType == Type.WATER){
                    return 2;
                } else if (defenderType == Type.FIRE) {
                    return 0.5;
                }else {
                return 1;}
            }

            }
            return 1.0;
    }
}

