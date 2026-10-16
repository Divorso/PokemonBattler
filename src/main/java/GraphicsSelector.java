public interface GraphicsSelector {
    void getMessage(String message);
    void getPokedexIntro(String message);
    void getBattleIntro(String message);
    void getEmptySpace(int number);
    void getLoadingTimer(int number);
    void getLoadingTimer(int number, String message);
}
