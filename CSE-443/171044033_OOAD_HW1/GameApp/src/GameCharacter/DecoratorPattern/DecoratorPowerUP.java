package GameCharacter.DecoratorPattern;

/**
 * decorator power up abstract class that containts point per score
 * (get score coefficient)
 */
public abstract class DecoratorPowerUP
{
    /**
     * abstract method
     * @return score coefficient
     */
    public abstract int PointsPerScore();
}