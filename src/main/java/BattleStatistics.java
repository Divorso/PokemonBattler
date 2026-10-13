public class BattleStatistics {
    private int playerWins;
    private int cpuWins;

    public BattleStatistics(int playerWins, int cpuWins){
        this.playerWins = playerWins;
        this.cpuWins = cpuWins;
    }
    public void addPlayerWin(){
        playerWins++;
    }
    public void addCpuWin(){
        cpuWins++;
    }

    public void showStatistics() {
        System.out.println("Vinster: " + playerWins);
        System.out.println("Förluster: " + cpuWins);
    }
}
