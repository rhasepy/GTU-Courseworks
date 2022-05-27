package Game;

/**
 * Launcer class
 */
public class Launcher
{
    /**
     * main method that start main scene and add component of GameLabel to this main scene
     * @param args main arguments
     */
    public static void main(String[] args) {
        MainScene mainScene = new MainScene("171044033_Empires_n_Puzzles");
        GameEngine gameUI = new GameEngine();
        mainScene.add(gameUI);
        mainScene.setVisible(true);
    }
}
