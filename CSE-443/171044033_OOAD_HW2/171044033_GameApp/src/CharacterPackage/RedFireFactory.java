package CharacterPackage;

import java.awt.*;

/**
 * Red Character Factory
 */
public class RedFireFactory implements CharacterFactory
{
    /**
     * getStrength
     * @return strength of base red character
     */
    @Override
    public double getStrength() { return 100; }

    /**
     * get agility
     * @return agility of base red character
     */
    @Override
    public double getAgility() { return 125; }

    /**
     * get health
     * @return health of base red character
     */
    @Override
    public double getHealth() { return 75; }

    /**
     * get color
     * @return color of base red character
     */
    @Override
    public Color getColor() { return Color.RED; }
}
