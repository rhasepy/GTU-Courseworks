package GameCharacter.StrategyPattern;

/**
 * Strategic interface.
 * Each class implements according to its own strategy.
 */
public interface JumpStrategy
{
    /**
     * It must subtracts a bound value (the y coordinate that can be max).
     * The more numbers are subtracted, the higher it jumps.
     * @param upper_bound, bound
     * @return, percentage of the bound to jump to within a bound
     */
    public int doJump(int upper_bound);
}
