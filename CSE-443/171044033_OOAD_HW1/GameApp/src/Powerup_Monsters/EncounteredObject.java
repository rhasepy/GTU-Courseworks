package Powerup_Monsters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

/**
 * This class will generate an object that the character encounters.
 * This object can be monster, powerup or highjump and will be randomly generated.
 */
public class EncounteredObject
{
    /**
     * x position of object
     */
    private int posX;

    /**
     * y position of object
     */
    private int posY;

    /**
     * Pixel to shift left per unit
     */
    private int speed;

    /**
     * Real position of x. X to remember after reset
     */
    private int realposX;

    /**
     * Graphic image on UI
     */
    private BufferedImage graphic;

    /**
     * Object type (Monster, Powerup etc.)
     */
    private CounterObjectType typeCounter;

    /**
     * Constructor
     * @param posX, x position
     * @param posY, y position
     * speed, speed value default 2
     */
    public EncounteredObject(int posX, int posY) {
        this.realposX = posX;
        this.posX = posX;
        this.posY = posY;
        this.speed = 2;
        this.typeCounter = CounterObjectType.MONSTER;
    }

    /**
     * Constructor
     * @param posX, x position
     * @param posY, y position
     * @param speed, speed value
     */
    public EncounteredObject(int posX, int posY, int speed) {
        this.realposX = posX;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.typeCounter = CounterObjectType.MONSTER;
        try {
            this.graphic = ImageIO.
                    read(new FileInputStream(new File("Resource/Monster.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set speed
     * @param speed, speed
     */
    public void setSpeed(int speed) { this.speed = speed; }

    /**
     * set graphic image
     * @param graphic, graphic image
     */
    public void setGraphic(BufferedImage graphic) { this.graphic = graphic; }

    /**
     * set position of x
     * @param x, x position
     */
    public void setPosX(int x) { this.posX = x; }

    /**
     * set position of y
     * @param y, x position
     */
    public void setPosY(int y) { this.posY = y; }

    /**
     * set type of object
     * @param typeCounter, type of countered object
     */
    public void setTypeCounter(CounterObjectType typeCounter) { this.typeCounter = typeCounter; }

    /**
     * getter method
     * @return x position
     */
    public int getPosX() { return this.posX; }

    /**
     * getter method
     * @return y position
     */
    public int getPosY() { return this.posY; }

    /**
     * getter method
     * @return graphic image
     */
    public BufferedImage getGraphic() { return graphic; }

    /**
     * getter method
     * @return speed value
     */
    public int getSpeed() { return speed; }

    /**
     * getter method
     * @return type of encountered object
     */
    public CounterObjectType getType() { return typeCounter; }

    /**
     * It is a method that randomly changes itself. Subtract 10% buff, 60% monster.
     */
    public void changeType_random() {
        Random rand = new Random();
        int rand_ = rand.nextInt(21);
        if (rand_ >= 0 && rand_ <= 8) {
            this.typeCounter = CounterObjectType.MONSTER;
            try {
                this.graphic = ImageIO.
                        read(new FileInputStream(new File("Resource/Monster.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rand_ >= 9 && rand_ <= 10) {
            this.typeCounter = CounterObjectType.X2_Power;
            try {
                this.graphic = ImageIO.
                        read(new FileInputStream(new File("Resource/X2.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rand_ >= 11 && rand_ <= 12) {
            this.typeCounter = CounterObjectType.X5_Power;
            try {
                this.graphic = ImageIO.
                        read(new FileInputStream(new File("Resource/X5.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rand_ >= 13 && rand_ <= 14) {
            this.typeCounter = CounterObjectType.X10_Power;
            try {
                this.graphic = ImageIO.
                        read(new FileInputStream(new File("Resource/X10.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (rand_ >= 15 && rand_ <= 16) {
            this.typeCounter = CounterObjectType.HighJump;
            try {
                this.graphic = ImageIO.
                        read(new FileInputStream(new File("Resource/HighJump.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.typeCounter = CounterObjectType.MONSTER;
            try {
                this.graphic = ImageIO.
                        read(new FileInputStream(new File("Resource/Monster.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Restart coordinates of this object
     */
    public void restartObject() {
        this.posX = realposX;
    }

    /**
     * run method of this object
     * @return, it will return true if it can go, false if it can't.
     */
    public boolean runObject() {

        if(this.posX > this.speed) {
            this.posX -= this.speed;
            return true;
        }

        return false;
    }
}