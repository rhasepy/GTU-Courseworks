/**
 * Proxy pattern support thread safety on best ds ever class
 */
public class ProxyBestDSEver implements BestDSEverSubject
{
    /**
     * real subject
     */
    private RealBestDSEverSubject realSubject;

    /**
     * Mutex for access on data structure synchronized
     */
    private final Object lock = new Object();

    /**
     * Constructor
     */
    public ProxyBestDSEver() {
        this.realSubject = new RealBestDSEverSubject();
    }

    /**
     * synchronized insert method insert for thread safety
     * @param o, object
     */
    @Override
    public void insert(Object o) {
        synchronized (lock) {
            System.out.println("Insert Start");
            this.realSubject.insert(o);
            System.out.println("Insert finish");
        }
    }

    /**
     * synchronized remove method insert for thread safety
     * @param o, object
     */
    @Override
    public void remove(Object o) {
        synchronized (lock) {
            System.out.println("Remove start");
            this.realSubject.remove(o);
            System.out.println("Remove finish");
        }
    }

    /**
     * synchronized get method for thread safety
     * @param index index of data
     * @return o, data index th
     */
    @Override
    public Object get(int index) {
        synchronized (lock) {
            System.out.println("Get start");

            try {
                System.out.println("Get finish");
                return realSubject.get(index);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
    }
}