import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedArrayList<E> extends AbstractList<E> implements List<E>
{
    /**
     * Head of linked array list node
     * It is a null on starting
     */
    private Node <E> head = null;

    /**
     * Tail of linked array list node
     * IT is a null on starting
     */
    private Node <E> tail = null;

    /**
     * Size of element of this data structure
     */
    private int size = 0;

    /**
     * The class that holds datas together
     * The class static because Node<E> of one List<E> can be the same type as the nodes from every other List<E>.
     *
     */
    private static class Node <E>
    {
        /**
         * holds data in one node together
         * the array provides this case
         */
        private E[] data;

        /**
         * it is the beginning of the node structure.
         */
        private Node <E> next = null;

        /**
         * it is the previous of the node structure.
         */
        private Node <E> prev = null;

        /**
         * The maximum number of data in each node is 5.
         */
        private static final int ARR_CAPACITY = 5; // my auxiliary

        /**
         * shows how much the element is used in the array.
         */
        private int ARR_USED = 0; // my auxiliary

        /**
         * One parameter constructor of node
         * @param dataItem, it represents the first element of the array in the node. created like this
         */
        @SuppressWarnings("unchecked")
        private Node(E dataItem)
        {
            data = (E[]) new Object[ARR_CAPACITY];
            add(ARR_USED, dataItem);
        }


        /**
         * Two parameter constructor of node
         * @param dataItem, it represents the first element of the array in the node. created like this
         * @param nodeRef, which node it will be connected to
         */
        @SuppressWarnings("unchecked")
        private Node(E dataItem, Node <E> nodeRef)
        {
            data = (E[]) new Object[ARR_CAPACITY];
            data[0] = dataItem;
            prev = nodeRef.prev;
            next = nodeRef;
        }

        /**
         * No parameter constructor
         * creates a hollow cell node.
         */
        @SuppressWarnings("unchecked")
        private Node()
        {
            data = (E[]) new Object[ARR_CAPACITY];
        }

        /**
         * // my auxiliary method
         * @return how much the array is used in a node
         */
        public int getUsed() { return ARR_USED; } // my auxiliary

        /**
         * // my auxiliary method
         * @return total size of the array in a node
         */
        public int getCapacity() { return ARR_CAPACITY; }

        /**
         *  // my auxiliary method
         * @throws  ArrayIndexOutOfBoundsException, if the given index is incorrect
         * @param index, index of array
         * @return index th element of array
         */
        public E get(int index)
        {
            if(index < 0 || index >= ARR_USED)
                throw new ArrayIndexOutOfBoundsException(index);

            return data[index];
        }

        /**
         *  // my auxiliary method
         * @throws  ArrayIndexOutOfBoundsException, if the given index is incorrect
         * @param index, index of array
         * @param newItem, set the newItem to index th element of array
         */
        public E set(int index, E newItem)
        {
            if(index < 0 || index >= ARR_USED)
                throw new ArrayIndexOutOfBoundsException(index);

            E oldItem = data[index];
            data[index] = newItem;

            return oldItem;
        }

        /**
         *  // my auxiliary method
         * @param newItem, will adding to array
         * @return if method can add return true, if method can't add return false
         */
        public boolean add(E newItem)
        {
            if(ARR_USED >= ARR_CAPACITY)
                return false;

            data[ARR_USED++] = newItem;

            return true;
        }

        /**
         * // my auxiliary method
         * @param index, will adding to array with index
         * @param newItem, will adding to array
         * @return if method can add return true, if method can't add return false
         */
        public boolean add(int index, E newItem)
        {
            if(index > ARR_USED || index < 0)
                throw new ArrayIndexOutOfBoundsException(index);

            if(ARR_USED >= ARR_CAPACITY)
                return false;

            for(int i = ARR_USED ; i > index ; --i)
                data[i] = data[i - 1];

            data[index] = newItem;
            ++ARR_USED;

            return true;
        }

        /**
         * // my auxiliary method
         * @param index, will removing index of element
         * @return removed element
         */
        public E remove(int index)
        {
            if(index < 0 || index >= ARR_USED)
                throw new ArrayIndexOutOfBoundsException(index);

            E removingItem = data[index];

            for(int i = (index + 1) ; i < ARR_USED ; ++i)
                data[i - 1] = data[i];

            --ARR_USED;

            return removingItem;
        }

        /**
         * // my auxiliary method
         * @return true, if the array is full
         * @return false, if tje array is not full
         */
        public boolean isFull() { return (ARR_USED >= ARR_CAPACITY); }

        /**
         * elements of array in one node that be converted to string
         * @return toString override
         */
        @Override
        public String toString()
        {
            String returnItem = "[";

            for(int i = 0 ; i < ARR_USED ; ++i)
            {
                returnItem += data[i].toString();

                if ( i < (ARR_USED - 1) )
                    returnItem += ", ";
            }

            returnItem += "]\n";

            return  returnItem;
        }
    }


    /**
     * Overriding add method
     * It adds with the help of iterator. It is explained in detail in iterator.
     * @param index, index to be added
     * @param newItem, element to be added
     */
    @Override
    public void add(int index, E newItem) { listIterator(index).add(newItem); }


    /**
     * Overriding add method
     * It adds with the help of iterator. It is explained in detail in iterator.
     * @param newItem, element to be added to last element of structure
     */
    @Override
    public boolean add(E newItem)
    {
        this.add(this.size(), newItem);
        return true;
    }

    /**
     * Overriding set method
     * It sets with the help of iterator. It is explained in detail in iterator.
     * @param index, index to be set
     * @param newItem, element to be set
     */
    @Override
    public E set(int index, E newItem)
    {
        E oldItem = this.get(index);

        listIterator(index).set(newItem);

        return oldItem;
    }

    /**
     * Overriding get method
     * It gets with the help of iterator. It is explained in detail in iterator.
     * @param index, index to be get
     */
    @Override
    public E get(int index) { return listIterator(index).next(); }

    /**
     * Overriding remove method
     * It removes with the help of iterator. It is explained in detail in iterator.
     * @param index, index to be removed
     */
    @Override
    public E remove(int index)
    {
        E removingElement = get(index);

        listIterator(index).remove();

        return removingElement;
    }

    /**
     * Overriding indexOf method
     * @param target, target object
     * @return index of target object if it is found
     * @return -1 target object if it is not found
     */
    @Override
    public int indexOf(Object target)
    {
        ListIterator <E> iter = this.iterator();

        if(target == null) {

            return -1;

        } else {

            while(iter.hasNext()) {
                if(target.equals(iter.next())) {
                    //iter.next();
                    return iter.previousIndex();
                }
            }
        }

        return -1;
    }

    /**
     * The method return size of structure
     * @return how much elements in structure
     */
    @Override
    public int size() { return size; }

    /**
     * elements of array in one node that be converted to string
     * @return toString override
     */
    @Override
    public String toString()
    {
        Node <E> temp = head;
        StringBuilder str = new StringBuilder();

        while(temp != null)
        {
            str.append(temp.toString());
            temp = temp.next;
        }

        return str.toString();
    }

    /**
     * generate iterator.
     * @return generate and return start of iterator
     */
    public ListIterator<E> iterator() { return new LinkedArrayListIterator(); }

    /**
     * generate iterator
     * @param index, index(according to element not node!) th iterator
     * @return generate and return index th iterator
     */
    public ListIterator<E> listIterator(int index) { return new LinkedArrayListIterator(index); }


    /**
     * The iterator class
     */
    private class LinkedArrayListIterator implements ListIterator<E>
    {
        /**
         * iterators looks at nextItem
         */
        private Node <E> nextItem;

        /**
         * behind the iterator
         */
        private Node <E> lastItemReturned;

        /**
         * node of structure
         */
        private int index = 0; // node index

        /**
         * element of node
         */
        private int element_index; // The element to process on index // this number between 0 and capacity-1 //

        /**
         * no parameter constructor
         * iter will be head node
         */
        public LinkedArrayListIterator()
        {
            nextItem = head;
            lastItemReturned = null;
            index = 0;
            element_index = -1;
        }

        /**
         * one parameter constructor
         * Things are a little different here. iterator has to work a little smart.
         * You have to advance by looking at the elements in the node.
         * It progresses without knowing what is in front of the normal iterator structure, only returns.
         * But in this structure, you should look at the arrays and advance between the nodes,
         * And here method, an iterator is created according to which element of which node looks at which index.
         * @param i, explained above
         * @throws IndexOutOfBoundsException, An iterator cannot exceed the index number.
         */
        public LinkedArrayListIterator(int i)
        {
            if (i < 0 || i > size)
                throw new IndexOutOfBoundsException("Invalid index " + i);

            lastItemReturned = null;
            nextItem = head;

            if(head != null)
            {
                int max_index_inNode = head.getUsed() - 1;

                while (max_index_inNode < i && nextItem.next != null) {

                        i -= nextItem.getUsed();
                        nextItem = nextItem.next;
                        max_index_inNode = nextItem.getUsed() - 1;
                        ++index;
                }

                element_index = i;
            }
        }

        /**
         *This iterator structure is a smart iterator
         * that knows which node and which element it is looking at and adds according to this information.
         *  But these additions must be checked.
         *  For example, if there is an element to be added, all elements should be shifted 1 unit from that element.
         * @param e, element to be added
         */
        @Override
        public void add(E e)
        {
            if(head == null) {   // if the structure is empty
                head = new Node <E> (e);
                tail = head;
                ++size;

            } else {

                if(nextItem.isFull() && nextItem.next == null && element_index == nextItem.getCapacity()) {
                    //This is where the element will be added to the end, not shifting.

                    Node <E> newNode = new Node<E>(e);
                    nextItem.next = newNode;
                    tail = newNode;
                    ++size;

                } else if(element_index < nextItem.getUsed()) { // to be shifting

                    if(!nextItem.isFull()){

                        nextItem.add(element_index, e);
                        ++size;

                    } else{

                        E shift_element = null;
                        E temp_shift = null;
                        boolean isFullAll = true;

                        shift_element = nextItem.remove(nextItem.getUsed()-1);
                        nextItem.add(element_index, e);
                        nextItem = nextItem.next;

                        while(nextItem != null && isFullAll)
                        {
                            if(nextItem.isFull())
                            {
                                temp_shift = nextItem.remove(nextItem.getUsed()-1);
                                nextItem.add(0, shift_element);
                                nextItem = nextItem.next;
                                shift_element = temp_shift;

                            } else {
                                isFullAll = false;
                                nextItem.add(0, shift_element);
                            }
                        }

                        if(isFullAll)
                        {
                            Node <E> newNode = new Node<E>(shift_element);
                            tail.next = newNode;
                            newNode.prev = tail;
                            tail = newNode;
                        }

                        ++size;
                    }

                } else { // Adding a standard adds to the last element of the array.

                    nextItem.add(element_index, e);
                    ++size;

                }
            }
        }

        /**
         * Can the iterator progress?
         * @return true, it can
         * @return false, it can't
         */
        @Override
        public boolean hasNext() { return (nextItem != null); }

        /**
         * Can the iterator progress back?
         * @return true, it can
         * @return false, it can't
         */
        @Override
        public boolean hasPrevious() { return (nextItem == null && size != 0) || (nextItem.prev != null); }

        /**
         * Knowing which element is on which node, the iterator sets here.
         * @param e, to be set
         */
        @Override
        public void set(E e) { nextItem.data[element_index] = e; }

        /**
         * Yes, this is the part that makes our iterator smart.
         * Iterator looks at the element here and when it finishes, it jumps to the next node.
         * @return, element of which element is on which node
         */
        @Override
        public E next()
        {
            if(!hasNext())
                throw new NoSuchElementException("There in not this element in list structre...\n");

            lastItemReturned = nextItem;
            if(element_index == nextItem.getUsed())
            {
                nextItem = nextItem.next;
                element_index = -1;
                ++index;
            }

            if(nextItem == null)
                throw new NoSuchElementException("There in not this element in list structre...\n");

            if(element_index == nextItem.getCapacity()-1)
                return lastItemReturned.data[element_index++];

            return lastItemReturned.data[++element_index];

        }

        /**
         * I did not implement this place smart because the user may only want to navigate between the nodes,
         * while doing this by doing the previous from the tail, but it should be noted that every nodes returns the 0th element.
         */
        @Override
        public E previous()
        {
            if(!hasPrevious())
                throw new NoSuchElementException();

            if(nextItem == null)
                nextItem = tail;
            else
                nextItem = nextItem.prev;

            lastItemReturned = nextItem;
            --index;

            return lastItemReturned.data[0];
        }

        /**
         * @return next index
         */
       @Override
        public int nextIndex()
        {
            if(nextItem == null)
                throw new NullPointerException("There is not next index...\n");

            return element_index+1;
        }

        /**
         * @return prev index
         */
        @Override
        public int previousIndex()
        {
            if(index == 0)
                throw new NullPointerException("There is not previous index...");

            return element_index-1;
        }

        /**
         * Which node and which element the iterator was holding the counter were created accordingly.
         * deletes the element according to these data.
         */
        @Override
        public void remove()
        {
            nextItem.remove(element_index);
            --size;

            if(nextItem.getUsed() == 0 && nextItem != head && nextItem != tail) {

                nextItem.prev.next = nextItem.next;
                nextItem.next.prev = nextItem.prev;

            } else if(nextItem.getUsed() == 0 && nextItem == head) {

                head = nextItem.next;
                head.prev = null;

            } else if(nextItem.getUsed() == 0 && nextItem == tail) {

                nextItem.prev.next = null;

            }
        }
    }
}
