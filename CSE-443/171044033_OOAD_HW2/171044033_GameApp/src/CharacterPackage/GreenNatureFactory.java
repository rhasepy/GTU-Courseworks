package CharacterPackage;

import java.awt.*;

/**
 * Base Green Character Factory class
 */
public class GreenNatureFactory implements CharacterFactory
{
    /**
     * get strength
     * @return stregth of base green character
     */
    @Override
    public double getStrength() { return 75; }

    /**
     * agility
     * @return strength of base green character
     */
    @Override
    public double getAgility() { return 100; }

    /**
     * health
     * @return health of base green character
     */
    @Override
    public double getHealth() { return 125; }

    /**
     * color
     * @return color of base green character
     */
    @Override
    public Color getColor() { return Color.GREEN; }
}
