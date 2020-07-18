/**
 * Binary Search Tree class
 * @author Muharrem Ozan Yesiller 171044033
 */
public class BinarySearchTree< E extends Comparable<E> > extends BinaryTree<E> implements SearchTree<E>
{
    /**
     * Return value from the public add method
     */
    protected boolean addReturn;

    /**
     * Return value from the public remove method
     */
    protected E deleteReturn;

    /**
     * The item object must be implemented the Comparable interface
     * @param item, the object being inserted
     * @return true if the object is inserted, false if the object already existy in the tree
     */
    @Override
    public boolean add(E item)
    {
        root = add(root, item);
        return addReturn;
    }

    /**
     * The item object must be implemented the Comparable interface
     * @param item, the object being inserted
     * @return The new local root that now contains the inserted item
     */
    private Node<E> add(Node<E> localRoot, E item)
    {
        if(localRoot == null) {
            addReturn = true;
            return new Node<E>(item);

        } else if(item.compareTo(localRoot.data) == 0) {

            addReturn = false;
            return localRoot;

        } else if(item.compareTo(localRoot.data) < 0) {

            localRoot.left = add(localRoot.left, item);
            return localRoot;

        } else {

            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Contains method
     * @param target, searching item
     * @return true, the data structure has target; otherwise, false
     */
    @Override
    public boolean contains(E target) { return (find(target) != null); }

    /**
     * The target object must be implemented the Comparable interface
     * @param target, The comparable object being sought
     * @return The object, if found otherwise null
     */
    @Override
    public E find(E target) { return find(root, target); }

    /**
     * recursive find method
     * @param target, The comparable object being sought
     * @return The object, if found otherwise null
     */
    private E find(Node<E> localRoot, E target)
    {
        if(localRoot == null)
            return null;

        int compResult = target.compareTo(localRoot.data);

        if(compResult == 0)
            return localRoot.data;

        else if(compResult < 0)
            return find(localRoot.left, target);

        else
            return find(localRoot.right, target);
    }

    /**
     * Delete element method
     * @param target to be delete element
     * @return the deleting is success return true otherwise return false
     */
    @Override
    public E delete(E target)
    {
       root = delete(root, target);
       return deleteReturn;
    }

    /**
     * Delete element method
     * @param target to be delete element
     * @return return deleting node
     */
    private Node<E> delete(Node<E> localRoot, E target)
    {
        if(localRoot == null)
        {
            deleteReturn = null;
            return localRoot;
        }

        int compRes = target.compareTo(localRoot.data);

        if(compRes < 0) {

            localRoot.left = delete(localRoot.left, target);
            return localRoot;

        } else if(compRes > 0) {

            localRoot.right = delete(localRoot.right, target);
            return localRoot;

        } else {
            deleteReturn = localRoot.data;

            if(localRoot.left == null)
                return localRoot.right;
            else if(localRoot.right == null)
                return localRoot.left;
            else
            {
                if(localRoot.left.right == null)
                {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                }

                else
                    localRoot.data =  findLargestChild(localRoot.left);

                return localRoot;
            }
        }
    }

    /**
     * Find the node that is the inorder predecessor and replace it
     * with its left child
     * @param parent, The parent of possible inorder precedessor
     * @return The data in the inorder precedessor
     */
    private E findLargestChild(Node<E> parent)
    {
        if(parent.right.right == null) {

            E returnVal = parent.right.data;
            parent.right = parent.right.left;
            return returnVal;

        } else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * Remove element method
     * Using delete method
     * Difference of delete, this method return boolean
     * @param target to be remove element
     * @return return true if removing is success otherwise false
     */
    @Override
    public boolean remove(E target)
    {
        root = delete(root, target);
        return deleteReturn != null;
    }
}
