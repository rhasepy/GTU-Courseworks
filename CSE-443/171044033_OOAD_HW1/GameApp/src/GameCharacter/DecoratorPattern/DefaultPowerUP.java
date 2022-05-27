package GameCharacter.DecoratorPattern;

/**
 * Default power up extend decoretor power up
 * Default coefficient is 1
 */
public class DefaultPowerUP extends DecoratorPowerUP
{
    /**
     * get point per score.
     * @return 1 because its default coefficient
     */
    @Override
    public int PointsPerScore() { return 1; }
}
