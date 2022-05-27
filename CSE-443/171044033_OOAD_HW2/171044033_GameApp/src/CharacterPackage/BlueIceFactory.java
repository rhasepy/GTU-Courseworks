package CharacterPackage;

import java.awt.*;

/**
 * Blue Factory class
 */
public class BlueIceFactory implements CharacterFactory
{
    /**
     * getter
     * @return strength of base blue character
     */
    @Override
    public double getStrength() { return 125; }

    /**
     * getter
     * @return agility of base blue character
     */
    @Override
    public double getAgility() { return 75; }

    /**
     * getter
     * @return health of base blue character
     */
    @Override
    public double getHealth() { return 100; }

    /**
     * getter
     * @return color of base blue character
     */
    @Override
    public Color getColor() { return Color.BLUE; }
}
