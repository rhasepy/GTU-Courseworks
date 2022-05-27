package GameCharacter.StrategyPattern;

/**
 * Jump context to provide a context for transitions between High and Low jump.
 */
public class JumpContext
{
    /**
     * Jump strategy object
     */
    private JumpStrategy jumpStrategy;

    /**
     * consturtor
     * @param jumpStrategy jump strategy (maybe high or low)
     */
    public JumpContext(JumpStrategy jumpStrategy) {
        this.jumpStrategy = jumpStrategy;
    }

    /**
     * It subtracts a bound value (the y coordinate that can be max).
     * The more numbers are subtracted, the higher it jumps.
     * @param upper_bound, bound
     * @return, percentage of the bound to jump to within a bound
     */
    public int executeStrategy(int upper_bound) {
        return jumpStrategy.doJump(upper_bound);
    }
}
