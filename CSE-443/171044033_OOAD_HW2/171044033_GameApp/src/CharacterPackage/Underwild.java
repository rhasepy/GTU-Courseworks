package CharacterPackage;

import java.awt.*;
import java.rmi.NoSuchObjectException;

/**
 * Underwild style character class
 */
public class Underwild extends GameCharacter
{
    /**
     * constructor
     * @param charSpec character spec factory object
     */
    public Underwild(CharacterFactory charSpec) {
        this.charSpec = charSpec;
        this.health = -1.0;
        this.agility = -1.0;
        this.strength = -1.0;
    }

    /**
     * constructor
     * @throws NoSuchObjectException this object can not uses no parameter constructor
     */
    public Underwild() throws NoSuchObjectException { throw new NoSuchObjectException("This object contains CharacterFactory..."); }

    /**
     * get agility method multiplied coefficient underwild style
     * @return agility of underwild style character
     */
    @Override
    public double getAgility() { return (this.agility = 1.6 * this.charSpec.getAgility()); }

    /**
     * get strength method multiplied coefficient underwild style
     * @return strength of underwild style character
     */
    @Override
    public double getStrength() { return (this.strength = 0.8 * this.charSpec.getStrength()); }

    /**
     * get health method multiplied coefficient underwild style
     * @return health of underwild style character
     */
    @Override
    public double getHealth() {
        if (this.health == -1.0) {
            return (this.health = 0.8 * this.charSpec.getHealth());
        }
        else {
            return this.health;
        }
    }

    /**
     * get color method
     * @return color of character
     */
    @Override
    public Color getColor() { return (this.color = this.charSpec.getColor()); }

    /**
     * get style method
     * @return style of character
     */
    @Override
    public CharacterStyle getStyle() { return (this.style = CharacterStyle.UNDERWILD); }
}
