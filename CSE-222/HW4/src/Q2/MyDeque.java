import java.util.AbstractCollection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDeque<E> extends AbstractCollection<E> implements Deque<E>
{
    /**
     * head of data of my deque
     */
    private Node <E> front;

    /**
     * tail of data of my deque
     */
    private Node <E> rear;

    /**
     * head of removed data
     * because garbage collecting and using memory efficiently
     */
    private Node <E> removed;

    /**
     * size of deque
     */
    private int size;

    /**
     * My Node class to keep the data together
     */
    private static class Node <E>
    {
        /**
         * data of node
         */
        private E data;

        /**
         * next of node
         */
        private Node <E> next = null;

        /**
         * previous of node
         */
        private Node <E> prev = null;

        /**
         * one parameter constructor
         * @param dataItem, will adding data in list
         */
        private Node(E dataItem) { data = dataItem; }
    }

    /**
     * No parameter constructor Deque
     * initializing
     */
    public MyDeque()
    {
        front = null;
        rear = null;
        removed = null;
        size = 0;
    }

    /**
     * @return size of deque
     */
    @Override
    public int size() { return size; }

    /**
     * Adding head of deque
     * @param e, element to add
     */
    @Override
    public void addFirst(E e)
    {
        if(!offerFirst(e))
            throw new IllegalArgumentException("The " + e + " is illegal...");
    }

    /**
     * Adding rear of deque
     * @param e, element to add
     */
    @Override
    public void addLast(E e)
    {
        if(!offerLast(e))
            throw new IllegalArgumentException("The " + e + " is illegal...");
    }

    /**
     * Adding first of deque
     * @param e, element to add
     * @return true, if the adding is success
     * @@return false, if the adding is not success
     */
    @Override
    public boolean offerFirst(E e)
    {
        try{

            this.iterator().add(e);

        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    /**
     * Adding last of deque
     * @param e, element to add
     * @return true, if the adding is success
     * @return false, if thhe adding is not success
     */
    @Override
    public boolean offerLast(E e)
    {
        try{

            this.descendingIterator().add(e);

        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    /**
     * Removing head of deque
     * @return true, if the removing is success
     * @return false, if the removing is not success
     */
    @Override
    public E removeFirst()
    {
        E returnVal;

        if((returnVal = this.pollFirst()) == null)
            throw new NoSuchElementException("You can not remove element! Deque is empty...");

        return returnVal;
    }

    /**
     * Removing rear of deque
     * @return true, if the removing is success
     * @return false, if the removing is not success
     */
    @Override
    public E removeLast()
    {
        E returnVal;

        if((returnVal = this.pollLast()) == null)
            throw new NoSuchElementException("You can not remove element! Deque is empty...");

        return returnVal;
    }

    /**
     * Removing head of deque
     * @return true, if the removing is success
     * @return false, if the removing is not success
     */
    @Override
    public E pollFirst()
    {
        if(this.isEmpty())
            return null;

        E returnVal = front.data;
        iterator().remove();

        return returnVal;
    }

    /**
     * Removing rear of deque
     * @return true, if the removing is success
     * @return false, if the removing is not success
     */
    @Override
    public E pollLast() {
        if(this.isEmpty())
            return null;

        E returnVal = rear.data;
        descendingIterator().remove();

        return returnVal;
    }

    /**
     * get method, gets head data of deque
     * @return head data of deque
     */
    @Override
    public E getFirst()
    {
        if(this.peekFirst() == null)
            throw new NoSuchElementException("The deque is empty...");

        return this.peekFirst();
    }

    /**
     * get method, gets rear data of deque
     * @return rear data of deque
     */
    @Override
    public E getLast()
    {
        if(this.peekLast() == null)
            throw new NoSuchElementException("The deque is empty...");

        return this.peekLast();
    }

    /**
     * Retrieves, but does not remove
     * @return  the first element of this deque
     */
    @Override
    public E peekFirst()
    {
        if(this.isEmpty())
            return null;

        return iterator().next();
    }

    /**
     * Retrieves, but does not remove
     * @return  the last element of this deque
     */
    @Override
    public E peekLast()
    {
        if(this.isEmpty())
            return null;

        return descendingIterator().previous();
    }

    /**
     * Removes the first occurrence of the specified element from this deque.
     * @param o, specified element
     * @return true, if element removed
     * @return false, if element could not removed
     */
    @Override
    public boolean removeFirstOccurrence(Object o)
    {
        Node<E> traverse = front;

        if(front == null || rear == null)
            throw new NullPointerException("Deque is empty...");

        while(traverse.next != null)  // traverse on front
        {
            if(traverse.data.equals(o)) break;

            traverse = traverse.next;
        }

        // ABOVE STRUCTURE OF IF ELSE is SOME POSSIBILITIES

        if(traverse.next == null && traverse.prev != null) { // remove from last

            rear = rear.prev;

            if(rear != null){
                //To add to removed to understand whether the structure is single-element or multi-element

                rear.next.next = removed;
                removed = rear.next;
                removed.prev = null;
                rear.next = null;

            } else
                front = null;

        } else if(traverse.prev == null) { // remove from first

            front = front.next;

            if(front != null) {
                //To add to removed to understand whether the structure is single-element or multi-element

                front.prev.next = removed;
                removed = front.prev;
                removed.prev = null;
                front.prev = null;

            } else
                rear = null;

        } else { // remove from middle

            traverse.prev.next = traverse.next;
            traverse.next.prev = traverse.prev;

            traverse.next = removed;
            removed = traverse;
            removed.prev = null;

            return true;

        }

        return false;
    }

    /**
     * Removes the last occurrence of the specified element from this deque.
     * @param o, specified element
     * @return true, if element removed
     * @return false, if element could not removed
     */
    @Override
    public boolean removeLastOccurrence(Object o)
    {
        Node<E> traverse = rear;

        if(front == null || rear == null)
            throw new NullPointerException("Deque is empty...");

        while(traverse.prev != null)  // traverse on rear
        {
            if(traverse.data.equals(o)) break;

            traverse = traverse.prev;
        }

        // ABOVE STRUCTURE OF IF ELSE is SOME POSSIBILITIES

        if(traverse.prev == null && traverse.next != null) { //first

            front = front.next;

            if(front != null) {
                //To add to removed to understand whether the structure is single-element or multi-element

                front.prev.next = removed;
                removed = front.prev;
                removed.prev = null;
                front.prev = null;

            } else
                rear = null;

        } else if(traverse.next == null) { // last

            rear = rear.prev;

            if(rear != null){
                //To add to removed to understand whether the structure is single-element or multi-element

                rear.next.next = removed;
                removed = rear.next;
                removed.prev = null;
                rear.next = null;

            } else
                front = null;

        } else { // middle

            traverse.prev.next = traverse.next;
            traverse.next.prev = traverse.prev;

            traverse.next = removed;
            removed = traverse;
            removed.prev = null;

            return true;
        }

        return false;
    }

    /**
     * Inserts the specified element into the queue represented by this deque
     * (in other words, at the tail of this deque)
     * @param e, specified element
     * @return true, adding is success
     * @return false, adding is not success
     */
    @Override
    public boolean offer(E e) { return offerLast(e); }

    /**
     * Retrieves and removes the head of the queue represented by this deque
     * @return removed element
     */
    @Override
    public E remove() { return removeFirst(); }

    /**
     * Retrieves and removes the head of the queue represented by this deque
     * (in other words, the first element of this deque),
     * @return removed element
     */
    @Override
    public E poll() { return pollFirst(); }

    /**
     * Retrieves, but does not remove
     * @return the first element of this deque
     */
    @Override
    public E element() { return getFirst(); }

    /**
     * Retrieves, but does not remove
     * @return  the first element of this deque
     */
    @Override
    public E peek() { return peekFirst(); }

    /**
     * Pushes an element onto the stack represented by this deque
     * at the head of this deque
     * @param e, specified element
     */
    @Override
    public void push(E e) { addFirst(e); }

    /**
     * Pops an element from the stack represented by this deque.
     * @return popped element of deque
     */
    @Override
    public E pop() { return removeFirst(); }

    /**
     * Inserts the specified element into the queue represented by this deque
     * at the tail of this deque
     * @param e, specified element
     * @return true, adding is success
     * @return false, adding is not success
     */
    @Override
    public boolean add(E e) { return offerLast(e); }

    /**
     * Returns true if this deque contains the specified element.
     * @param o, object to search
     * @return true, if object contains
     * @return false, object do not contains
     */
    @Override
    public boolean contains(Object o)
    {
        Iterator<E> it = this.iterator();

        if (o == null)
        {
            while(it.hasNext())
                if (it.next() == null)
                    return true;
        }

        else
        {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }

            return false;
    }

    /**
     * Overriding to string methods
     * Print removed elements(proof of working of methods) and elements of deque
     * @return string of deque
     */
    @Override
    public String toString()
    {
        System.out.println("GARBAGE OF DEQUE: ");
        Node <E> traverse = removed;

        while(traverse != null)
        {
            System.out.print(traverse.data);
            traverse = traverse.next;
            if(traverse != null)
                System.out.print(", ");
        }

        System.out.println("\n--------------");

        StringBuilder sb = new StringBuilder();

        DequeIterator iterator = iterator();

        sb.append("Content of Deque: \n");
        while(iterator.hasNext())
        {
            sb.append(iterator.next());

            if(iterator.hasNext())
                sb.append(",");

            sb.append(" ");
        }

        sb.append("\n");

        return sb.toString();
    }

    /**
     * @return an iterator over the elements in this deque in reverse sequential order.
     */
    @Override
    public DequeIterator descendingIterator() { return new DequeIterator(this.size()); }

    /**
     * @return Returns an iterator over the elements in this deque in proper sequence.
     */
    @Override
    public DequeIterator iterator() { return new DequeIterator(0); }

    /*
    	HOCAM BU KISMI TURKCE YAZMAK ISTEDIM TAM OLARAK KENDIMI IFADE ETMEK ICIN
    	DEQUEITERATOR CLASSI BIR ITERATOR<E> IMPLEMENTIDIR KULLANICI BU CLASSIN ITERATORUNU CAGIRDIGINDA
    	ITERATOR<E> GIBI KULLANABILECEK FAKAT BEN VERILERIMI HER TURLU EKLEME CIKARMA ISLEMI CONSTANT TIME YANI O(1)
    	OLSUN DIYE DOUBLE LINKED LIST OLARAK TUTTUM VE DEQUEITERATOR ICERISINDE PRIVATE OLARAK PREVIOUS, HAS PREVIOUS GIBI METHODLAR IMPLEMENT ETTIM
    	KULLANICININ BU METHODLARDAN HABERI OLMAYACAK PROTOTIP BOZULMUYOR

    	LISTITERATOR<E> DE IMPLEMENT EDEBILIRDIM AMA KULLANICI LISTITERATOR<E> DEGIL DE ITERATOR OLARAK BILIYOR GELEN ITERATORU BU YUZDEN YAPMADIM BUNLARI
    	EK OLARAK BELIRTMEK ISTEDIM, VERI YAPISI SORUNSUZ ,PROTOTIPLERE UYGUN CALISIYOR.
    */

    /**
     * Iterator class
     */
    private class DequeIterator implements Iterator<E>
    {
        /**
         * next item of iterator
         */
        private Node <E> nextItem;

        /**
         * last item returned of iterator
         */
        private Node <E> lastItemReturned;

        /**
         * iterator index
         */
        private int index = 0;

        /**
         * One parameter constructor
         * @param i, index of iterator
         */
        public DequeIterator(int i)
        {
            if(i < 0 || i > size())
                throw new IndexOutOfBoundsException("Invalid index " + i + "...");

            if(i == 0) // start of iterator
            {
                index = i;
                nextItem = front;
            }

            else if(i == size) // last of iterator (this block for doing  O(1))
            {
                index = size() - 1;
                nextItem = rear.next;
            }

            else
            {
                nextItem = front;
                for(index = 0 ; index < i ; ++index)
                    nextItem = nextItem.next;
            }
        }

        /**
         * @return true if this list iterator has more elements when traversing the list in the forward direction.
         */
        @Override
        public boolean hasNext() { return nextItem != null; }

        /**
         * @return the next element in the list and advances the cursor position.
         */
        @Override
        public E next()
        {
            if(!hasNext())
                throw new NoSuchElementException("There is illegal element...");

            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        /**
         * @return true if this list iterator has more elements when traversing the list in the reverse direction.
         */
        private boolean hasPrevious() { return (nextItem == null && size != 0) || nextItem.prev != null; }

        /**
         * @return the previous element in the list and moves the cursor position backwards.
         */
        private E previous()
        {
            if (!hasPrevious())
                throw new NoSuchElementException("There is illegal element...");

            if(nextItem == null)
                nextItem = rear;
            else
                nextItem = nextItem.prev;

            lastItemReturned = nextItem;
            index--;

            return lastItemReturned.data;
        }

        /**
         * @return  the index of the element that would be returned by a subsequent call to next().
         */
        private int nextIndex()
        {
            this.next();

            return this.index;
        }

        /**
         * @return the index of the element that would be returned by a subsequent call to previous().
         */
        private int previousIndex()
        {
            this.previous();

            return this.index;
        }

        /**
         * from the deque the last element that was returned by next() or previous() (optional operation).
         */
        @Override
        public void remove()
        {
            if(front == null || size() == 0)
                throw new NullPointerException();

            else if(nextItem == front) // head of deq
            {
                if(removed == null) {

                    removed = front;
                    front = front.next;

                    if(front == null)
                        rear = null;
                    else
                        front.prev = null;

                    removed.next = null;

                } else {

                    removed.prev = front;
                    front = front.next;
                    removed.prev.next = removed;
                    removed = removed.prev;

                    if(front == null)
                        rear = null;
                    else
                        front.prev = null;

                    removed.prev = null;
                }
            }

            else if(nextItem == null) // last of deq
            {
                if (removed == null) {

                    removed = rear;
                    rear = rear.prev;

                    if(rear == null)
                        front = null;
                    else
                        rear.next = null;

                    removed.prev = null;

                } else{

                    removed.prev = rear;
                    rear = rear.prev;
                    removed.prev.next = removed;
                    removed = removed.prev;

                    if(rear == null)
                        front = null;
                    else
                        rear.next = null;

                    removed.prev = null;
                }
            }

            else { throw new UnsupportedOperationException(); }
            //because this is a deletion in the middle of deque.

            --size;
        }

        /**
         * This method throw exception because
         * It does not support the Deque set method.
         */
        private void set(E e) { throw new UnsupportedOperationException(); }

        /**
         * Inserts the specified element into the list (optional operation).
         * @param e, specified element
         */
        private void add(E e)
        {
            if(front == null) // first with head null
            {
                if(removed == null) {

                    front = new Node<E>(e);
                    rear = front;

                } else if (removed.next != null) {

                    removed = removed.next;
                    front = removed.prev;
                    front.data = e;
                    front.next = null;
                    front.prev = null;
                    rear = front;

                } else {

                    front = removed;
                    front.data = e;
                    rear = front;
                    removed = null;
                }
            }

            else if(nextItem == front) // first with head not null
            {
                if(removed == null) {

                    Node<E> newNode = new Node<E>(e);
                    newNode.next = nextItem;
                    nextItem.prev = newNode;
                    front = newNode;

                } else if (removed.next != null){

                    removed = removed.next;
                    removed.prev.next = nextItem;
                    nextItem.prev = removed.prev;
                    front = removed.prev;
                    front.data = e;
                    front.prev = null;
                    removed.prev = null;

                } else {

                    removed.next = nextItem;
                    nextItem.prev = removed;
                    front = removed;
                    front.data = e;
                    removed = null;
                }
            }

            else if(nextItem == null) // last
            {
                if(removed == null) {

                    Node<E> newNode = new Node<E>(e);
                    rear.next = newNode;
                    newNode.prev = rear;
                    rear = newNode;

                } else if (removed.next != null) {

                    removed = removed.next;
                    rear.next = removed.prev;
                    removed.prev.prev = rear;
                    rear = removed.prev;
                    rear.data = e;
                    rear.next = null;
                    removed.prev = null;

                } else {

                    rear.next = removed;
                    removed.prev = rear;
                    rear = removed;
                    rear.data = e;
                    removed = null;

                }
            }

            else // middle
            {
                throw new UnsupportedOperationException();
                //because this is a adding in the middle of deque.
            }

            ++size;
            ++index;
        }
    }
}
