public interface Countable
{
    /**
     * setter method
     * @param x new count value
     */
    void setCount(int x);

    /**
     * getter method
     * @return count value
     */
    int getCount();

    /**
     * increment count value
     */
    void incrementCount();

    /**
     * decrement count value
     */
    void decrementCount();
}
