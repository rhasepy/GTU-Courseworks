/**
 * Best DS Ever subject interface
 */
public interface BestDSEverSubject
{
    /**
     * insert method
     * @param o, object to be inserted
     */
    void insert(Object o);

    /**
     * remove method
     * @param o, object to be removed
     */
    void remove(Object o);

    /**
     * get method
     * @param index, index number
     * @return object to be indexth element int data structure
     */
    Object get(int index);
}
