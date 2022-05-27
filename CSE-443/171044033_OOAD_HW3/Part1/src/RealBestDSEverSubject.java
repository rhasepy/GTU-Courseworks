import java.util.ArrayList;

/**
 * Real subject
 */
public class RealBestDSEverSubject implements BestDSEverSubject
{
    /**
     * Arraylist
     */
    private ArrayList<Object> nonSynch_DS;

    /**
     * Consturctor
     */
    public RealBestDSEverSubject() {
        this.nonSynch_DS = new ArrayList<>();
    }

    /**
     * insert method
     * @param o, object to be inserted
     */
    @Override
    public void insert(Object o) {
        nonSynch_DS.add(o);
    }

    /**
     * remove method
     * @param o, object to be removed
     */
    @Override
    public void remove(Object o) {
        nonSynch_DS.remove(o);
    }

    /**
     * get method
     * @param index, index number
     * @return object to be indexth
     */
    @Override
    public Object get(int index) {

        if (index >= nonSynch_DS.size()) {
            return null;
        }
        return nonSynch_DS.get(index);
    }
}
