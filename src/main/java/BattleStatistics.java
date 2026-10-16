import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


public class BattleStatistics{
    private int playerWins;
    private int cpuWins;
    private HashMap<String, Integer> pokemonWins;
    private HashMap<String, Integer> pokemonLosses;
    private HashMap<String, Integer> pokemonUsage;
    private HashMap<String, Integer> attackUsage;


    @JsonCreator
    public BattleStatistics(
            @JsonProperty("playerWins") int playerWins,
            @JsonProperty("cpuWins") int cpuWins,
            @JsonProperty("pokemonWins") HashMap<String, Integer> pokemonWins,
            @JsonProperty("pokemonLosses") HashMap<String, Integer> pokemonLosses,
            @JsonProperty("pokemonUsage") HashMap<String, Integer> pokemonUsage,
            @JsonProperty("attackUsage") HashMap<String, Integer> attackUsage)
    {

        this.playerWins = playerWins;
        this.cpuWins = cpuWins;
        this.pokemonWins = pokemonWins != null ? pokemonWins : new HashMap<>();
        this.pokemonLosses = pokemonLosses != null ? pokemonLosses : new HashMap<>();
        this.pokemonUsage = pokemonUsage != null ? pokemonUsage : new HashMap<>();
        this.attackUsage = attackUsage != null ? attackUsage : new HashMap<>();

    }

    public void addPlayerWin(String pokemonName){
        playerWins++;
        pokemonWins.put(pokemonName, pokemonWins.getOrDefault(pokemonName,0)+1);
    }
    public void addCpuWin(String pokemonName){
        cpuWins++;
        pokemonLosses.put(pokemonName, pokemonLosses.getOrDefault(pokemonName,0)+1);
    }
    public void addPokemonUsage(String pokemonName) {
        pokemonUsage.put(pokemonName, pokemonUsage.getOrDefault(pokemonName, 0) + 1);
    }
    public void addAttackUsage(String attackName) {
        attackUsage.put(attackName, attackUsage.getOrDefault(attackName, 0) + 1);
    }

    public void showMostUsed(){
        String mostUsedPokemon = "";
        String mostUsedAttack = "";
        int pokemonCount = 0;
        int attackCount = 0;

        for(String pokemonName : pokemonUsage.keySet()){
            int count = pokemonUsage.get(pokemonName);
            if(count >  pokemonCount){
                pokemonCount = count;
                mostUsedPokemon = pokemonName;
            }
        }
        for (String attackName : attackUsage.keySet()) {
            int count = attackUsage.get(attackName);
            if (count > attackCount) {
                attackCount = count;
                mostUsedAttack = attackName;
            }
        }

        if(pokemonCount > 0){
            System.out.printf("Mest använda Pokemon: %s\n", mostUsedPokemon);
        }
        if (attackCount > 0){
            System.out.printf("Mest använda Attack: %s\n", mostUsedAttack);
        }

    }
    public void showStatistics() {
        System.out.println("Vinster: " + playerWins);
        System.out.println("Förluster: " + cpuWins);

        System.out.println("\nStatstik för dina Pokemon:");
        for(String pokemonName : pokemonWins.keySet()){
            showPokemonStatistic(pokemonName);
        }
        for (String pokemonName : pokemonLosses.keySet()) {
            if (!pokemonWins.containsKey(pokemonName)) {
                showPokemonStatistic(pokemonName);
            }
        }
    }
    public void showPokemonStatistic(String pokemonName) {
        int wins = pokemonWins.getOrDefault(pokemonName, 0);
        int losses = pokemonLosses.getOrDefault(pokemonName, 0);
        double winRate = 0.0;

        if (wins + losses == 0) {
            winRate = 0;
        } else {
            winRate = (double) wins / (wins + losses) * 100;
        }

        System.out.printf("%s: %d vinster, %d förluster (%.1f%% win rate)%n",
                pokemonName, wins, losses, winRate
        );
    }

    public int getPlayerWins() {
        return playerWins;
    }
    public int getCpuWins() {
        return cpuWins;
    }
    public HashMap<String, Integer> getPokemonUsage() {
        return pokemonUsage;
    }
    public HashMap<String, Integer> getAttackUsage() {
        return attackUsage;
    }
    public HashMap<String, Integer> getPokemonWins() {
        return pokemonWins;
    }
    public HashMap<String, Integer> getPokemonLosses() {
        return pokemonLosses;
    }

}
