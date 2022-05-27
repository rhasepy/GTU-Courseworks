public class Launcher
{
    /**
     * main method that start main scene and add component of GameLabel to this main scene
     * @param args main arguments
     */
    public static void main(String[] args) {
        MainScene mainScene = new MainScene("171044033_ConcurencyPart");
        ThreadOptionPannel DFT_UI = new ThreadOptionPannel();
        mainScene.add(DFT_UI);
        mainScene.setVisible(true);
    }
}
