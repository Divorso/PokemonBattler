import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class BattleStatistics{
    private int playerWins;
    private int cpuWins;

    @JsonCreator
    public BattleStatistics(@JsonProperty("playerWins") int playerWins, @JsonProperty("cpuWins") int cpuWins){
        this.playerWins = playerWins;
        this.cpuWins = cpuWins;
    }

    public void addPlayerWin(){
        playerWins++;
    }
    public void addCpuWin(){
        cpuWins++;
    }
    public int getPlayerWins() {
        return playerWins;
    }
    public int getCpuWins() {
        return cpuWins;
    }

    public void showStatistics() {
        System.out.println("Vinster: " + playerWins);
        System.out.println("Förluster: " + cpuWins);
    }
}
