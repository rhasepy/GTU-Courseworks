package CharacterPackage;

import java.awt.*;
import java.rmi.NoSuchObjectException;

/**
 * Atlantis style game character
 */
public class Atlantis extends GameCharacter
{
    /**
     * constructor
     * @param charSpec char specs factory object
     */
    public Atlantis(CharacterFactory charSpec) {
        this.charSpec = charSpec;
        this.health = -1.0;
        this.agility = -1.0;
        this.strength = -1.0;
    }

    /**
     * no parameter constructor
     * @throws NoSuchObjectException this object can not uses no parameter constructor
     */
    public Atlantis() throws NoSuchObjectException { throw new NoSuchObjectException("This object contains CharacterFactory..."); }

    /**
     * getter
     * @return agility
     */
    @Override
    public double getAgility() { return (this.agility = 1.2 * this.charSpec.getAgility()); }

    /**
     * getter
     * @return strength
     */
    @Override
    public double getStrength() { return (this.strength = 0.8 * this.charSpec.getStrength()); }

    /**
     * getter
     * @return health
     */
    @Override
    public double getHealth() {
        if (this.health == -1.0) {
            return (this.health = 1.2 * this.charSpec.getHealth());
        }
        else {
            return this.health;
        }
    }

    /**
     * getter
     * @return color
     */
    @Override
    public Color getColor() { return (this.color = this.charSpec.getColor()); }

    /**
     * getter
     * @return style of character
     */
    @Override
    public CharacterStyle getStyle() { return (this.style = CharacterStyle.ATLANTIS); }
}
