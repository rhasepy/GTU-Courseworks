package Game;

import GameCharacter.Character;
import GameCharacter.DecoratorPattern.DefaultPowerUP;
import GameCharacter.DecoratorPattern.PowerUP_A;
import GameCharacter.DecoratorPattern.PowerUP_B;
import GameCharacter.DecoratorPattern.PowerUP_C;
import GameCharacter.StrategyPattern.HighJump;
import GameCharacter.StrategyPattern.JumpContext;
import GameCharacter.StrategyPattern.LowJump;
import Powerup_Monsters.CounterObjectType;
import Powerup_Monsters.EncounteredObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * Game UI Panel object implements some listener for request of user
 */
public class GameUI extends JPanel implements KeyListener, ActionListener
{
    /**
     * main character
     */
    private Character main_char;

    /**
     * encountered object that means monster, powerups etc
     */
    private EncounteredObject encounteredObject;

    /**
     * background image
     */
    private BufferedImage background;

    /**
     * pause image
     */
    private BufferedImage pauseImage;

    /**
     * start image
     */
    private BufferedImage pressStart;

    /**
     * health image
     */
    private BufferedImage healthImage;

    /**
     * score label
     */
    private JLabel scoreLabel;

    /**
     * start button
     */
    private JButton start_button;

    /**
     * pause button
     */
    private JButton pause_button;

    /**
     * exit button
     */
    private JButton exit_button;

    /**
     * fps label
     */
    private JTextArea FPS_Label;

    /**
     * fps label 2
     */
    private JTextArea FPS_Label2;

    /**
     * log text area
     */
    private JTextArea logArea;

    /**
     * log area wrapper pane
     */
    private JScrollPane logArea_pane;

    /**
     * surround y backgoround image
     */
    private static final int AXIS_Y_BACKGROUND = 470;

    /**
     * start x coordinate of background
     */
    private int startPoint_background;

    /**
     * last refrest time for calculate FPS
     */
    private long lastFrameRefreshTime;

    /**
     * 'd' button interrupt handler
     * If the keyboard driver does not press two keys at the same time,
     * if one presses and the other key works, there will be an interrupt.
     * But we can understand that the user takes his hand from this key.
     * If the key has been interrupted and hasn't taken off, it means it's still pressing.
     */
    private boolean dChecker;

    /**
     * 'space' button interrupt handler
     * If the keyboard driver does not press two keys at the same time,
     * if one presses and the other key works, there will be an interrupt.
     * But we can understand that the user takes his hand from this key.
     * If the key has been interrupted and hasn't taken off, it means it's still pressing.
     */
    private boolean sChecker;

    /**
     * thread start of not start
     */
    private boolean thread_start_state;

    /**
     * game paused or not
     */
    private boolean pauseState;

    /**
     * the game start or not
     */
    private boolean isStart;

    /**
     * the game finish or not
     */
    private boolean finishState;

    /**
     * high jump enable or disable
     */
    private boolean highJumpEnabled;

    /**
     * Thread running the game
     */
    private Thread gameLoopThread;

    /**
     * constructor
     * initiliaze components
     * initilaize ui components
     * initiliaze thread
     */
    public GameUI() {
        super(null);
        this.COMPONENT_INIT();
        this.UI_INIT();
        this.THREAD_INIT();
    }

    /**
     * thread init method
     * It implements the thread but does not start it.
     */
    private void THREAD_INIT() {
        this.gameLoopThread = new Thread(new Runnable() {
            @Override
            public void run(){
                thread_start_state = true;
                while(true)
                {
                    if (isStart && (!pauseState)) {
                        if (main_char.getJumpState()) {
                            main_char.downCharacter();
                        }
                        if (dChecker) {
                            main_char.runCharacter();
                            startPoint_background -= main_char.getSpeed();
                            if (!encounteredObject.runObject()) {
                                encounteredObject.restartObject();
                                if (encounteredObject.getType() == CounterObjectType.MONSTER)
                                    main_char.incrementScorePoint();
                                scoreLabel.setText("Score: " + main_char.getScore());
                                encounteredObject.changeType_random();
                            }
                        }
                        if (sChecker) {
                            main_char.jumpCharacter();
                        }

                        if (anyIntersects()) {
                            controlFinish();
                        } else {
                            finishState = true;
                        }
                    }
                    repaint();
                    try { Thread.sleep(9); }
                    catch (Exception e) { e.printStackTrace(); }
                }
            }
        });
    }

    /**
     * component initiliaze for game engine
     */
    private void COMPONENT_INIT() {
        int surround = 350;
        this.main_char = new Character(0, surround, 3, 3);
        this.encounteredObject = new EncounteredObject(950 , surround, this.main_char.getSpeed());
        this.pauseState = false;
        this.isStart = false;
        this.dChecker = false;
        this.sChecker = false;
        this.thread_start_state = false;
        this.lastFrameRefreshTime = 0;
        this.highJumpEnabled = false;
    }

    /**
     * ui component initiliaze
     */
    private void UI_INIT() {
        this.requestFocus();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.setBackground(Color.DARK_GRAY);
        this.startPoint_background = 0;

        start_button = new JButton("Start");
        pause_button = new JButton("Pause");
        exit_button = new JButton("Exit");

        start_button.setBounds(0, 470, 70, 30);
        start_button.setBackground(Color.LIGHT_GRAY);
        start_button.setFocusable(false);
        start_button.setFont(new Font("Arial", Font.BOLD, 10));
        start_button.setVisible(true);
        start_button.addActionListener(event -> { this.startAction(); });
        this.add(start_button);

        pause_button.setBounds(75, 470, 70, 30);
        pause_button.setBackground(Color.LIGHT_GRAY);
        pause_button.setFocusable(false);
        pause_button.setFont(new Font("Arial", Font.BOLD, 10));
        pause_button.setVisible(true);
        pause_button.addActionListener(event -> { this.pauseAction(); });
        this.add(pause_button);

        exit_button.setBounds(150, 470, 70, 30);
        exit_button.setBackground(Color.LIGHT_GRAY);
        exit_button.setFocusable(false);
        exit_button.setFont(new Font("Arial", Font.BOLD, 10));
        exit_button.setVisible(true);
        exit_button.addActionListener(event -> { this.exitAction(); });
        this.add(exit_button);

        FPS_Label2 = new JTextArea();
        FPS_Label2.setBounds(225, 470, 55, 15);
        FPS_Label2.setFocusable(false);
        FPS_Label2.setBackground(Color.BLACK);
        FPS_Label2.setForeground(Color.LIGHT_GRAY);
        FPS_Label2.setFont(new Font("Arial", Font.BOLD, 10));
        FPS_Label2.setVisible(true);
        FPS_Label2.setText("FPS");
        this.add(FPS_Label2);

        scoreLabel = new JLabel();
        scoreLabel.setBounds(800, 470, 300, 30);
        scoreLabel.setFocusable(false);
        scoreLabel.setBackground(Color.WHITE);
        scoreLabel.setForeground(Color.LIGHT_GRAY);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        scoreLabel.setVisible(true);
        scoreLabel.setText("Score: 0");
        this.add(scoreLabel);

        FPS_Label = new JTextArea();
        FPS_Label.setBounds(0, 0, 55, 45);
        FPS_Label.setFocusable(false);
        FPS_Label.setBackground(Color.BLACK);
        FPS_Label.setForeground(Color.LIGHT_GRAY);
        FPS_Label.setFont(new Font("Arial", Font.BOLD, 10));
        FPS_Label.setVisible(true);
        FPS_Label.setText("FPS");
        this.add(FPS_Label);

        logArea = new JTextArea();
        logArea.setBounds(10, 510, 960, 210);
        logArea.setFocusable(false);
        logArea.setBackground(Color.DARK_GRAY);
        logArea.setForeground(Color.WHITE);
        logArea.setFont(new Font("Arial", Font.BOLD, 15));
        logArea.setVisible(true);
        String helloMSG = "To start: Start Button\n" +
                "To pause: Pause Button\n" +
                "To continues: Go Button (Pause button will change after click)\n" +
                "To exit: Exit Button\n" +
                "The hearths are your health point\n" +
                "The score is your score point";
        logArea.setText(helloMSG);
        this.add(logArea);

        logArea_pane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        logArea_pane.setBounds(10, 510, 970, 210);
        logArea_pane.setBackground(Color.DARK_GRAY);
        logArea_pane.setFocusable(false);
        logArea_pane.setVisible(true);
        this.add(logArea_pane);

        try {
            this.background = ImageIO.
                    read(new FileInputStream("Resource/Background.jpg"));

            this.pauseImage = ImageIO.
                    read(new FileInputStream("Resource/Pause.png"));

            this.pressStart = ImageIO.
                    read(new FileInputStream("Resource/PressStart.png"));

            this.healthImage = ImageIO.
                    read(new FileInputStream("Resource/health.png"));

        } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * Evaluates the situation when the main character hits an object.
     * Monster reduces health. Power up buffs the character. etc.
     */
    private void controlFinish() {

        if (this.finishState == true) {
            this.finishState = false;

            if (encounteredObject.getType() == CounterObjectType.MONSTER) {

                main_char.setHealth(main_char.getHealth() - 1);
                logArea.setText(logArea.getText() + "\nCharacter lost 1 health");
                if (main_char.getHealth() == 0) {
                    isStart = false;
                    logArea.setText(logArea.getText() + "\nGame finished!");
                }

            } else if (encounteredObject.getType() == CounterObjectType.HighJump) {

                if (highJumpEnabled) {
                    main_char.setJumpContext(new JumpContext(new LowJump()));
                    logArea.setText(logArea.getText() + "\nCharacter get 'low jump'");
                    highJumpEnabled = false;
                } else {
                    main_char.setJumpContext(new JumpContext(new HighJump()));
                    logArea.setText(logArea.getText() + "\nCharacter get 'high jump'");
                    highJumpEnabled = true;
                }

            } else if (encounteredObject.getType() == CounterObjectType.X2_Power) {
                main_char.setScoreDecorator(new PowerUP_A(main_char.getScoreDecorator()));
                logArea.setText(logArea.getText() + "\nCharacter get 'X2 BUF'");
            } else if (encounteredObject.getType() == CounterObjectType.X5_Power) {
                main_char.setScoreDecorator(new PowerUP_B(main_char.getScoreDecorator()));
                logArea.setText(logArea.getText() + "\nCharacter get 'X5 BUF'");
            } else if (encounteredObject.getType() == CounterObjectType.X10_Power) {
                main_char.setScoreDecorator(new PowerUP_C(main_char.getScoreDecorator()));
                logArea.setText(logArea.getText() + "\nCharacter get 'X10 BUF'");
            }
        }
    }

    /**
     * Did the main character collide with an artifact
     * @return true that means there is an intersect between char or encountered object
     * @return false that means there is not an intersect between char or encountered object
     */
    private boolean anyIntersects() {
        return new Rectangle(encounteredObject.getPosX() + 30, encounteredObject.getPosY(), 158/2, 178/2).
                intersects(new Rectangle(main_char.getPosX(), main_char.getPosY(), 158/2, 178/2));
    }

    /**
     * paint method
     * @param g graphics on main frame
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(background, startPoint_background, 0, 1024, AXIS_Y_BACKGROUND, this);
        if (startPoint_background < -1024) {
            startPoint_background = 0;
        }

        g.drawImage(background, startPoint_background + 1024, 0, 1024, AXIS_Y_BACKGROUND, this);
        g.drawImage(main_char.getGraphic(), main_char.getPosX(), main_char.getPosY(), 158/2, 178/2, this);
        g.drawImage(encounteredObject.getGraphic(), encounteredObject.getPosX(), encounteredObject.getPosY(), 158/2, 178/2, this);

        int posHealth = 300;
        for (int i = 0; i < main_char.getHealth(); ++i) {
            g.drawImage(healthImage, posHealth, 470, 70, 30, this);
            posHealth += 50;
        }

        if (!isStart) {
            g.drawImage(pressStart, 320, 370, 400, 100, this);
        }

        if (pauseState) {
            g.drawImage(pauseImage, 370, 300, 250, 250, this);
        }

        long timediff = System.nanoTime() - this.lastFrameRefreshTime;
        double seconds = (double) timediff / 1000000000;
        int FPS = (int) (1 / seconds);
        FPS_Label.setText(String.valueOf(FPS) + " FPS");
        FPS_Label2.setText(FPS_Label.getText());
        this.lastFrameRefreshTime = System.nanoTime();
    }

    /**
     * key pressed listener thread
     * @param keyEvent which button pressed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (isStart) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_D:
                    boolean logHandler = (!dChecker) && (isStart);
                    if (logHandler && (pauseState)) {
                        logArea.setText(logArea.getText() + "\nCharacter will run");
                    } else if(logHandler) {
                        logArea.setText(logArea.getText() + "\nCharacter running");
                    }
                    dChecker = true;
                    break;
                case KeyEvent.VK_SPACE:
                    if ((!sChecker) && (isStart) && (!pauseState)) {
                        if (highJumpEnabled) {
                            logArea.setText(logArea.getText() + "\nCharacter 'high' jumped");
                        } else {
                            logArea.setText(logArea.getText() + "\nCharacter 'low' jumped");
                        }
                    }
                    sChecker = true;
                    break;
            }
        }
    }

    /**
     * NOT USED
     * @param actionEvent, action event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {}

    /**
     * If the repaint call then paint call via Java
     */
    @Override
    public void repaint() { super.repaint(); }

    /**
     * Key typed lisntener thread
     * @param keyEvent, event keyboard
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    /**
     * For example:
     * 'd' button interrupt handler
     *  If the keyboard driver does not press two keys at the same time,
     * if one presses and the other key works, there will be an interrupt.
     * But we can understand that the user takes his hand from this key.
     * If the key has been interrupted and hasn't taken off, it means it's still pressing.
     * @param keyEvent, event keyboard
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_D) {
            dChecker = false;
            if ((!pauseState) && (isStart))
                logArea.setText(logArea.getText() + "\nCharacter stopped");
            else if ((pauseState) && (isStart))
                logArea.setText(logArea.getText() + "\nCharacter will stop");
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
            sChecker = false;
        }
    }

    /**
     * Start button listener and action
     */
    private void startAction() {

        if (!isStart) {
            if (!thread_start_state) {
                gameLoopThread.start();
            }
            this.pauseState = false;
            this.isStart = true;
            this.dChecker = false;
            this.sChecker = false;
            this.main_char.setScore(-1);
            this.main_char.setScoreDecorator(new DefaultPowerUP());
            scoreLabel.setText("Score: 0");
            this.main_char.setHealth(3);
            String helloMSG = "To start: Start Button\n" +
                    "To pause: Pause Button\n" +
                    "To continues: Go Button (Pause button will change after click)\n" +
                    "To exit: Exit Button\n" +
                    "The hearths are your health point\n" +
                    "The score is your score point";
            logArea.setText(helloMSG);
        }
    }

    /**
     * pause button action and listen
     */
    private void pauseAction() {
        if (this.isStart) {
            if (this.pauseState) {
                this.pause_button.setText("Pause");
                logArea.setText(logArea.getText() + "\nGame continues");
                this.pauseState = false;
            } else {
                this.pause_button.setText("Go");
                logArea.setText(logArea.getText() + "\nGame paused");
                this.pauseState = true;
            }
        }
    }

    /**
     * exit button action and listen
     */
    private void exitAction() { System.exit(1); }
}