package CharacterPackage;

import java.awt.*;
import java.rmi.NoSuchObjectException;

/**
 * Valhalla style game character class
 */
public class Valhalla extends GameCharacter
{
    /**
     * constructor
     * @param charSpec, character spec parameter as a character factory
     */
    public Valhalla(CharacterFactory charSpec) {
        this.charSpec = charSpec;
        this.health = -1.0;
        this.agility = -1.0;
        this.strength = -1.0;
    }

    /**
     * no parameter constructor
     * @throws NoSuchObjectException this object can not use no parameter constructor
     */
    public Valhalla() throws NoSuchObjectException { throw new NoSuchObjectException("This object contains CharacterFactory..."); }

    /**
     * Override get Agility method
     * @return agility with multiplied coefficient valhalla style
     */
    @Override
    public double getAgility() { return (this.agility = 0.4 * this.charSpec.getAgility()); }

    /**
     * override get strenght method
     * @return strength with multiplied coefficient valhalla style
     */
    @Override
    public double getStrength() { return (this.strength = 1.3 * this.charSpec.getStrength()); }

    /**
     * override get health method
     * @return health with multiplied coefficient valhalla style
     */
    @Override
    public double getHealth() {
        if (this.health == -1.0) {
            return (this.health = 1.3 * this.charSpec.getHealth());
        }
        else {
            return this.health;
        }
    }

    /**
     * override get color method
     * @return color of character
     */
    @Override
    public Color getColor() { return (this.color = this.charSpec.getColor()); }

    /**
     * override style methode
     * @return style of character
     */
    @Override
    public CharacterStyle getStyle() { return (this.style = CharacterStyle.VALHALLA); }
}