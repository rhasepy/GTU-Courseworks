package CharacterPackage;

import java.awt.*;

/**
 * Character factory interface
 */
public interface CharacterFactory
{
    /**
     * getter method
     * @return strength
     */
    double getStrength();

    /**
     * getter method
     * @return agility
     */
    double getAgility();

    /**
     * getter method
     * @return health
     */
    double getHealth();

    /**
     * getter method
     * @return color
     */
    Color getColor();
}
