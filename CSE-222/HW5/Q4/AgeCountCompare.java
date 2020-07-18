import java.util.Comparator;

/**
 * The comparator class compare 2 age data objects
 * @author Muharrem Ozan Yesiller 171044033
 */
public class AgeCountCompare implements Comparator<AgeData>
{
    /**
     * compare age data objects that has number of people
     * @param l_item, left value for equations
     * @param r_item, right value for equations
     * @return l_item number of people different of r_item number of people
     */
    @Override
    public int compare(AgeData l_item, AgeData r_item) { return l_item.getCount() - r_item.getCount(); }
}
