import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Pokemon {

    public static final int MIN_HP = 1;
    public static final int MAX_HP = 400;


    private String name;
    private int maxHP;
    private int currentHP;
    private Type type;
    private ArrayList<Attack> attacks;

    @JsonCreator
    public Pokemon(
            @JsonProperty("name") String name,
            @JsonProperty("maxHP") int maxHP,
            @JsonProperty("type") Type type,
            @JsonProperty("attacks") ArrayList<Attack> attacks
    ) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Pokemon måste ha ett namn");
        }

        if (maxHP < MIN_HP || maxHP > MAX_HP) {
            throw new IllegalArgumentException("Max HP måste vara mellan 1 - 400");
        }

        if (type == null) {
            throw new IllegalArgumentException("Pokemon måste ha en typ");
        }

        if (attacks == null || attacks.isEmpty() || attacks.size() > 4) {
            throw new IllegalArgumentException("Pokemon måste ha 1-4 attacker");
        }
        setName(name);

        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.type = type;
        this.attacks = attacks;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        currentHP = Math.max(0, currentHP - damage);
    }
    @JsonIgnore
    public boolean isDefeated() {
        return currentHP <= 0;
    }

    public void resetHP() {
        currentHP = maxHP;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank() || name.length() < 3) {
            throw new IllegalArgumentException("Pokemon måste ha ett namn och vara minst 3 bokstäver");
        }

        this.name = name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        if (maxHP < MIN_HP || maxHP > MAX_HP) {
            throw new IllegalArgumentException("Max HP måste vara mellan 1 - 400");
        }

        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type == null) {
            throw new IllegalArgumentException("Pokemon måste ha en typ");
        }

        this.type = type;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }
}