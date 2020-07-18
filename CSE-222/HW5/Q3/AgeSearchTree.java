/**
 * Generic Age Search Tree class
 * E extends Countable and Comparable because
 * E has age and countable field, according to these comparable
 * Can be used with various classes that meet these requirements.
 * @author Muharrem Ozan Yesiller 171044033
 */
public class AgeSearchTree<E extends Countable & Comparable<E>> extends BinarySearchTree<E>
{
    /**
     * add function for comparable and countable object
     * @param item, adding element
     * @return if the add success return true other than retrun false
     */
    @Override
    public boolean add(E item)
    {
        if(contains(item))
        {
            E temp = find(item);

            temp.incrementCount();
            return true;
        }

        return super.add(item);
    }

    /**
     * remove function for combarable and countable element
     * @param target, to be remove element
     * @return if the remove success return true other than return false
     */
    @Override
    public boolean remove(E target)
    {
        if(contains(target))
        {
            E temp = find(target);

            if(temp.getCount() > 1)
                temp.decrementCount();
            else
                super.remove(target);

            return true;
        }

        return false;
    }

    /**
     * Find class, search target element in the this data structure
     * @param target, to be search element
     * @return target if it found other than return null
     */
    @Override
    public E find(E target) { return super.find(target); }

    /**
     * It gives the total of the counter of the younger objects.
     * @param age, age value
     * @return total of the counter fo the younger than age value
     */
    public int youngerThan(int age) { return findYoungers(root, age); }

    /**
     * It gives the total of the counter of the younger objects.
     * @param age, age value
     * @return total of the counter fo the younger than age value
     */
    private int findYoungers(BinaryTree.Node<E> localRoot, int age)
    {
        if(localRoot == null)
            return 0;

        if(localRoot.data.getData() >= age)
            return findYoungers(localRoot.left, age);

        else
            return localRoot.data.getCount() + findYoungers(localRoot.right, age) + findYoungers(localRoot.left, age);
    }

    /**
     * It gives the total of the counter of the older objects.
     * @param age, age value
     * @return total of the counter fo the older than age value
     */
    public int olderThan(int age) { return findOlders(root, age); }

    /**
     * It gives the total of the counter of the older objects.
     * @param age, age value
     * @return total of the counter fo the older than age value
     */
    private int findOlders(BinaryTree.Node<E> localRoot, int age)
    {
        if(localRoot == null)
            return 0;

        if(localRoot.data.getData() <= age)
            return findOlders(localRoot.right, age);

        else
            return localRoot.data.getCount() + findOlders(localRoot.right, age) + findOlders(localRoot.left, age);
    }
}
