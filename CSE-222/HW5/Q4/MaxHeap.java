import java.util.ArrayList;

/**
 * Heap class, Key of heap "MAX NUMBER OF PEOPLE from AGEDATA class"
 * @author Muharrem Ozan Yesiller 171044033
 */
public class MaxHeap
{
    /**
     * ArrayList of AgeData object of heap data structure
     */
    private ArrayList<AgeData> heap;

    /**
     * No parameter constructor heap is empty
     */
    public MaxHeap() { heap = new ArrayList<AgeData>(); }

    /**
     * Add method into heap
     * @param o to be adding object
     */
    public void add(AgeData o)
    {
        int new_child_index;
        int parent_index;

        if(!heap.contains(o)) {
            heap.add(o);
            new_child_index = heap.size() - 1;
        }

        else {
            heap.get(heap.indexOf(o)).incrementCount();
            new_child_index = heap.indexOf(o);
        }

        parent_index = (new_child_index - 1) / 2;

        set_parent_forADD(parent_index, new_child_index);
    }
    
     /**
     * set/organize related tree after added
     * @param parent_i parent index
     * @param child_i child index
     */
    private void set_parent_forADD(int parent_i, int child_i)
    {
        AgeCountCompare comparator = new AgeCountCompare();

        while(parent_i >= 0 && (comparator.compare(heap.get(parent_i), heap.get(child_i)) < 0) )
        {
            AgeData temp = heap.get(parent_i);

            heap.set(parent_i, heap.get(child_i));
            heap.set(child_i, temp);

            child_i = parent_i;
            parent_i = (child_i - 1) / 2;
        }
    }

    /**
     * remove method
     * @param o to be remove object o
     */
    public void remove(AgeData o)
    {
        if(!heap.contains(o))
            throw new IllegalArgumentException("There is not " + o.toString() + " element in heap");

        else
        {
            int removed_index = heap.indexOf(o);

            if(heap.get(heap.indexOf(o)).getCount() > 1)
                heap.get(removed_index).decrementCount();

            else
            {
                AgeData temp = heap.get(heap.size() - 1);

                heap.set(heap.size() - 1, heap.get(removed_index));
                heap.set(removed_index, temp);

                heap.remove(heap.size() - 1);
            }

            set_parent_forREMOVE(removed_index);
        }
    }

    /**
     * Set/Organize related tree after removed element
     * @param parent parent index
     */
    private void set_parent_forREMOVE(int parent)
    {
        AgeCountCompare comparator = new AgeCountCompare();

        while(true)
        {
            int leftChild = (2 * parent) + 1;
            int rightChild = leftChild + 1;

            if(leftChild >= heap.size())
                break;

            int maxChild = leftChild;

            if(rightChild < heap.size() && comparator.compare(heap.get(leftChild), heap.get(rightChild)) < 0)
                maxChild = rightChild;

            if(comparator.compare(heap.get(parent), heap.get(maxChild)) < 0)
            {
                AgeData temp = heap.get(parent);

                heap.set(parent, heap.get(maxChild));
                heap.set(maxChild, temp);
                parent = maxChild;
            }
            else
                break;
        }
    }

    /**
     * find method
     * @param o to be search object
     * @return related object if found object, otherwise return null
     */
    public AgeData find(AgeData o)
    {
        if(heap.contains(o))
            return heap.get(heap.indexOf(o));

        return null;
    }

    /**
     * younger than method retrieve younger than number of people age that given parameter
     * @param age seracinh younger than this age
     * @return number of people of younger than age
     */
    public int youngerThan(int age)
    {
        int res = 0;

        for(AgeData o : heap)
            if(o.getAge() < age)
                res += o.getCount();

        return res;
    }

    /**
     * older than method retrieve older than number of people age that given parameter
     * @param age seracinh older than this age
     * @return number of people of older than age
     */
    public int olderThan(int age)
    {
        int res = 0;

        for(AgeData o : heap)
            if(o.getAge() > age)
                res += o.getCount();

        return res;
    }

    /**
     * String of Max heap object
     * @return String of Max Heap Object
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for(AgeData o : heap)
        {
            sb.append(o.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
