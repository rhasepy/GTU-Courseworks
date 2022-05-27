package GameCharacter.StrategyPattern;

/**
 * High jump class implement jump stragety interface
 */
public class HighJump implements JumpStrategy
{
    /**
     * constructor
     */
    public HighJump() {}

    /**
     * It subtracts a bound value (the y coordinate that can be max).
     * The more numbers are subtracted, the higher it jumps.
     * @param upper_bound, bound
     * @return, percentage of the bound to jump to within a bound
     */
    @Override
    public int doJump(int upper_bound) {
        return upper_bound - 250;
    }
}
