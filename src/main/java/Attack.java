import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Attack {
    public static final int MIN_DAMAGE = 1;
    public static final int MAX_DAMAGE = 50;

    public static final int MIN_ACCURACY = 1;
    public static final int MAX_ACCURACY = 100;

    private String attackName;
    private int baseDamage;
    private int accuracy;
    private Type type;

    @JsonCreator
    public Attack(
            @JsonProperty("attackName") String attackName,
            @JsonProperty("baseDamage") int baseDamage,
            @JsonProperty("accuracy") int accuracy,
            @JsonProperty("type") Type type
    ) {
        if (attackName == null || attackName.isBlank()) {
            throw new IllegalArgumentException("Attacknamn får ej vara tomt");
        }

        if (baseDamage < MIN_DAMAGE || baseDamage > MAX_DAMAGE) {
            throw new IllegalArgumentException("Base damage måste vara mellan 1 - 50");
        }

        if (accuracy < MIN_ACCURACY || accuracy > MAX_ACCURACY) {
            throw new IllegalArgumentException("Accuracy måste vara mellan 1 - 100");
        }

        if (type == null) {
            throw new IllegalArgumentException("Attack måste ha en type");
        }

        this.attackName = attackName;
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
        this.type = type;
    }

    public String getAttackName() {
        return attackName;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Type getType() {
        return type;
    }
}