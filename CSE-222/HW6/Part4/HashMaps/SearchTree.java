/**
 * Search Tree Interface
 * @author Muharrem Ozan Yesiller 171044033
 */
public interface SearchTree<E>
{
    /**
     * Add method
     * @param item to be add item
     * @return true if adding is success otherwise false
     */
    boolean add(E item);

    /**
     * contains method
     * @param target to be containing item
     * @return true if finding is success otherwise false
     */
    boolean contains(E target);

    /**
     * Find method
     * @param target to be find item
     * @return item if target is found otherwise return null
     */
    E find(E target);

    /**
     *  delete method
     * @param target to be delete
     * @return removed element
     */
    E delete(E target);

    /**
     *  remove method
     * @param target to be removed
     * @return removed element
     */
    boolean remove(E target);
}
