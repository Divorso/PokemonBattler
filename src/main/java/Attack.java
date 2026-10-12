import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Attack {

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

        if (baseDamage < 1 || baseDamage > 40) {
            throw new IllegalArgumentException("Base damage måste vara mellan 1 - 40");
        }

        if (accuracy < 1 || accuracy > 100) {
            throw new IllegalArgumentException("Accuracy måste vara 1 - 100");
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