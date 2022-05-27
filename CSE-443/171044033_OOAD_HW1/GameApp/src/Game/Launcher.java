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
        MainScene mainScene = new MainScene("171044033_Homework1_2DScrolling");
        GameUI gameUI = new GameUI();
        mainScene.add(gameUI);
        mainScene.setVisible(true);
    }
}
