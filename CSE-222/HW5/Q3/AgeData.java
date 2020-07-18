import java.security.InvalidParameterException;
/**
 * AgeData class that keeps age value and number of people value
 * @author Muharrem Ozan Yesiller 171044033
 */
public class AgeData implements Comparable<AgeData>, Countable
{
    /**
     * Age value
     */
    private int age;

    /**
     * Number of people value
     */
    private int count;

    /**
     * One parameter constructor
     * @param age, age value
     */
    public AgeData(int age)
    {
        this.age = age;
        this.count = 1;
    }

    /**
     * No parameter constructor age to be assigned 0 default
     */
    public AgeData()
    {
        this.age = 0;
        this.count = 1;
    }

    /**
     * setter method for age value
     * @param x, age value
     */
    public void setAge(int x)
    {
        if(x >= 0)
            this.age = x;
        else
            throw new InvalidParameterException("This parameter is not age...");
    }

    /**
     * setter method for number of people value
     * @param x, number of people value
     */
    public void setCount(int x)
    {
        if(x >= 0)
            this.count = x;
        else
            throw new InvalidParameterException("This parameter is not count...");
    }

    /**
     * incerement 1 number of people
     */
    public void incrementCount() { this.setCount(this.getCount() + 1); }

    /**
     * decrement 1 number of people
     */
    public void decrementCount() { this.setCount(this.getCount() - 1); }

    /**
     * get data for countable interface, to compare age and increment or decrement count
     * @return
     */
    @Override
    public int getData() { return age; }

    /**
     * getter method for age
     * @return age value
     */
    public int getAge() { return this.age; }

    /**
     * getter method for number of people
     * @return number of people
     */
    public int getCount() { return this.count; }

    /**
     * Override to string method
     * @return string of object
     */
    @Override
    public String toString()
    {
        return String.valueOf(age) + " - " +
                String.valueOf(count);

    }

    /**
     * Override equals method
     * @param o, should be Agedata object other than return false
     * @return compare and return true or false according to object of age value
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == this) return true;

        if(o instanceof AgeData)
        {
            AgeData temp = (AgeData) o;

            return temp.getAge() == this.getAge();
        }

        return false;
    }

    /**
     * compare override method
     * @param o, AgeData object
     * @return difference between this.Age and o.Age
     */
    public int compareTo(AgeData o) { return this.getAge() - o.getAge(); }
}
