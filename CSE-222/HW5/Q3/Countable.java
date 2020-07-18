/**
 * Countable interface can set count, get count, increment and decrement count and get count data
 * @author Muharrem Ozan Yesiller 171044033
 */
public interface Countable
{
    /**
     * setter method
     * @param x new count value
     */
    void setCount(int x);

    /**
     * setter method
     * @return count value
     */
    int getCount();

    /**
     * increment count value
     */
    void incrementCount();

    /**
     * devrement count value
     */
    void decrementCount();

    /**
     * getter method
     * @return count value
     */
    int getData();
}
