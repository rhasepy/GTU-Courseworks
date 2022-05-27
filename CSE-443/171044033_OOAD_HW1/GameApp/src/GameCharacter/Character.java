package GameCharacter;

import GameCharacter.DecoratorPattern.DecoratorPowerUP;
import GameCharacter.DecoratorPattern.DefaultPowerUP;
import GameCharacter.StrategyPattern.JumpContext;
import GameCharacter.StrategyPattern.LowJump;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 * Main char class.
 * The main character's functions are implemented in this class.
 */
public class Character
{
    /**
     * position x
     */
    private int posX;

    /**
     * position y
     */
    private int posY;

    /**
     * speed value of character
     */
    private int speed;

    /**
     * score of character
     */
    private int score;

    /**
     * health of character
     */
    private int health;

    /**
     * jump values of this character
     */
    private int jumpVal;

    /**
     * real position y of this character (to jump)
     */
    private int realPosY;

    /**
     * Motion effect sensitivity.
     * It does not appeal to the eye in very fast transitions.
     */
    private int moveSense;

    /**
     How many inputs will it give motion effect
     */
    private int moveSense_ctr;

    /**
     * jumped or not information
     */
    private boolean jumpState;

    /**
     * running, more running or stop information
     */
    private RunState run_state;

    /**
     * Strategy pattern jump information (high or low jump)
     */
    private JumpContext jContext;

    /**
     * graphic image of main char
     */
    private BufferedImage graphic;

    /**
     * decorative pattern object of score coefficient
     */
    private DecoratorPowerUP scoreCoefficient;

    /**
     * constructor
     * @param posX, position x
     * @param posY, position y
     * @param speed, speec value
     * @param jumpVal, jump value
     */
    public Character(int posX, int posY, int speed, int jumpVal) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.jumpVal = jumpVal;
        this.health = 3;
        this.score = 0;
        this.moveSense = 9;
        this.moveSense_ctr = 0;
        this.run_state = RunState.STOP;
        this.jumpState = false;
        this.realPosY = posY;
        try {
            this.graphic = ImageIO.
                    read(new FileInputStream(new File("Resource/PauseState.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.jContext = new JumpContext(new LowJump());
        this.scoreCoefficient = new DefaultPowerUP();
    }

    /**
     * setter method
     * @param posX, position x
     */
    public void setPosX(int posX) { this.posX = posX; }

    /**
     * setter method
     * @param posY position y
     */
    public void setPosY(int posY) { this.posY = posY; }

    /**
     * setter method
     * @param jumpVal, jump value
     */
    public void setJumpVal(int jumpVal) { this.jumpVal = jumpVal; }

    /**
     * setter method
     * @param moveSense, movesense
     */
    public void setMoveSense(int moveSense) { this.moveSense = moveSense; }

    /**
     * setter method
     * @param moveSense_ctr movesense ctr
     */
    public void setMoveSense_ctr(int moveSense_ctr) { this.moveSense_ctr = moveSense_ctr; }

    /**
     * setter method
     * @param run_state run state character
     */
    public void setRun_state(RunState run_state) { this.run_state = run_state; }

    /**
     * setter method
     * @param graphic image of character
     */
    public void setGraphic(BufferedImage graphic) { this.graphic = graphic; }

    /**
     * setter method
     * @param speed speed value
     */
    public void setSpeed(int speed) { this.speed = speed; }

    /**
     * setter method
     * @param jumpState jumped or not
     */
    public void setJumpState(boolean jumpState) { this.jumpState = jumpState; }

    /**
     * setter method
     * @param score score information
     */
    public void setScore(int score) { this.score = score; }

    /**
     * setter method
     * @param context jump context (high or low)
     */
    public void setJumpContext(JumpContext context) { this.jContext = context; }

    /**
     * setter method
     * @param scoreCoefficient score coefficient decoretor
     */
    public void setScoreDecorator(DecoratorPowerUP scoreCoefficient) { this.scoreCoefficient = scoreCoefficient; }

    /**
     * setter method
     * @param health healt value
     */
    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else if (health > 3) {
            this.health = 3;
        } else {
            this.health = health;
        }
    }

    /**
     * getter method
     * @return speed
     */
    public int getSpeed() { return speed; }

    /**
     * getter method
     * @return position x
     */
    public int getPosX() { return posX; }

    /**
     * getter method
     * @return position y
     */
    public int getPosY() { return posY; }

    /**
     * getter method
     * @return jump value
     */
    public int getJumpVal() { return jumpVal; }

    /**
     * getter method
     * @return move sensivity
     */
    public int getMoveSense() { return moveSense; }

    /**
     * getter method
     * @return move sensivity counter
     */
    public int getMoveSense_ctr() { return moveSense_ctr; }

    /**
     * getter method
     * @return run state of main char (run or more run or stop)
     */
    public RunState getRun_state() { return run_state; }

    /**
     * getter method
     * @return graphic of main character
     */
    public BufferedImage getGraphic() { return graphic; }

    /**
     * getter method
     * @return jump state
     */
    public boolean getJumpState() { return this.jumpState; }

    /**
     * getter method
     * @return score information
     */
    public int getScore() { return this.score; }

    /**
     * getter method
     * @return healt value
     */
    public int getHealth() { return this.health; }

    /**
     * getter method
     * @return get jump context high or low
     */
    public JumpContext getJumpContext() { return this.jContext; }

    /**
     * getter method
     * @return decorativible score coefficient object
     */
    public DecoratorPowerUP getScoreDecorator() { return this.scoreCoefficient; }

    /**
     * Increasing the score with the score coefficient.
     */
    public void incrementScorePoint() {
        this.score += this.scoreCoefficient.PointsPerScore();
        if (this.score == 0) {
            this.score += 1;
        }
    }

    /**
     * jump method of character
     * change y coordinates
     * If he has not fallen after jumping, he cannot jump again.
     */
    public void jumpCharacter() {

        if (false == this.jumpState) {
            this.posY = jContext.executeStrategy(Math.abs(this.posY - 0));
            this.jumpState = true;
        }
    }

    /**
     Fall of the jumping character.
     */
    public void downCharacter() {

        if (true == this.jumpState) {
            if (this.posY < this.realPosY) {
                this.posY += this.jumpVal;
            } else {
                this.jumpState = false;
            }
        }
    }

    /**
     * Character's run.
     * Actually, monsters come towards the character,
     * but this part is to give effect in terms of UI.
     */
    public void runCharacter() {
        if (run_state == RunState.STOP) {
            run_state = RunState.RUN1;
            try {
                this.graphic = ImageIO.
                        read(new FileInputStream(new File("Resource/Run_1_State.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (moveSense_ctr % moveSense == 0) {
            if (run_state == RunState.RUN1) {
                run_state = RunState.RUN2;
                try {
                    this.graphic = ImageIO.
                            read(new FileInputStream(new File("Resource/Run_2_State.png")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                run_state = RunState.RUN1;
                try {
                    this.graphic = ImageIO.
                            read(new FileInputStream(new File("Resource/Run_1_State.png")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ++moveSense_ctr;
    }
}