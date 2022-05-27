package GameCharacter.DecoratorPattern;

/**
 * A Power up the methods set coefficient * 2
 */
public class PowerUP_A extends DecoratorPowerUP
{
    /**
     * decoretor power up
     */
    private DecoratorPowerUP decorator;

    /**
     * constructor
     * @param decorator, default or another decorator
     */
    public PowerUP_A(DecoratorPowerUP decorator) {
        this.decorator = decorator;
    }

    /**
     * get point coefficient method
     * @return default * this point per second.
     */
    @Override
    public int PointsPerScore() {
        return this.decorator.PointsPerScore() * 2;
    }
}
