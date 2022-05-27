package CharacterPackage;

import java.awt.*;

/**
 * Game Character class
 */
public abstract class GameCharacter implements CharacterFactory {
    /**
     * CharacterFactory object for character specs.
     */
    CharacterFactory charSpec;

    /**
     * strength of character
     */
    double strength;

    /**
     * agility of character
     */
    double agility;

    /**
     * health of character
     */
    double health;

    /**
     * colod of character
     */
    Color color;

    /**
     * style of character
     */
    CharacterStyle style;

    /**
     * abstract getter method
     * @return should be agility
     */
    public abstract double getAgility();

    /**
     * abstract getter method
     * @return should be strength
     */
    public abstract double getStrength();

    /**
     * abstract health method
     * @return should be health
     */
    public abstract double getHealth();

    /**
     * abstract color method
     * @return should be color of character
     */
    public abstract Color getColor();

    /**
     * abstract style method
     * @return should be style of character
     */
    public abstract CharacterStyle getStyle();

    /**
     * take attack damage and update health value
     * @param damage if damage greater than health, then update health value to zero oterwise operate difference operation
     */
    public void takeAttack(double damage) {
        if (damage >= getHealth())
            this.health = 0.0;
        else
            this.health -= damage;
    }

    /**
     * get damage
     * @return 100 * power( (strength / agility), 1.35 )
     */
    public double getDamage() { return 100 * Math.pow(getStrength() / getAgility(), 1.35); }
}