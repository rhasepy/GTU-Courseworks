package GameCharacter.DecoratorPattern;

/**
 * B Power up the methods set coefficient * 2
 */
public class PowerUP_B extends DecoratorPowerUP
{
    /**
     * decoretor power up
     */
    private DecoratorPowerUP decorator;

    /**
     * constructor
     * @param decorator, default or another decorator
     */
    public PowerUP_B(DecoratorPowerUP decorator) {
        this.decorator = decorator;
    }

    /**
     * get point coefficient method
     * @return default * this point per second.
     */
    @Override
    public int PointsPerScore() {
        return this.decorator.PointsPerScore() * 5;
    }
}
