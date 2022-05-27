package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Main scene frame
 */
public class MainScene extends JFrame
{
    /**
     * constructor
     * @param title screen title
     */
    public MainScene(String title) {
        super(title);
        this.UI_INIT();
    }

    /**
     * init ui main frame screen
     */
    private void UI_INIT() {
        this.setResizable(false);
        this.setFocusable(false);
        this.setSize(512, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension window = this.getSize();
        Point centerPoint = GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getCenterPoint();
        this.setLocation(centerPoint.x - window.width / 2, centerPoint.y - window.height / 2);
    }
}