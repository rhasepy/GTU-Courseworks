import java.util.ArrayList;
import java.util.Random;

public class Main
{
    /**
     * threads
     */
    private static Thread[] threads;

    /**
     * thread size
     */
    private static final int THREAD_SIZE = 10;

    /**
     * main test
     */
    public static void testThreadSafety() {
            BestDSEverSubject ds = new ProxyBestDSEver();
            BestDSEverSubject ds_ = new RealBestDSEverSubject();

            threads = new Thread[THREAD_SIZE];

            System.out.println("Thread Safety Test Start");
            for (int i = 0; i < threads.length; ++i) {

                final int idx = i;
                threads[i]= new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Random r = new Random();
                        int rand = r.nextInt() % 3;

                        if (rand == 0) {
                            ds.insert(r.nextInt());
                        } else if (rand == 1) {
                            ds.remove(r.nextInt());
                        } else {
                            ds.get(r.nextInt());
                        }
                    }
                });
            }

            for (int i = 0; i < threads.length; ++i) {
                threads[i].start();
            }
            for (int i = 0; i < threads.length; ++i) {
                try {
                    threads[i].join();
                } catch (Exception e) {}
            }
            System.out.println("Thread Safety Test End\n");
        }

    /**
     * main
     * @param args commandline args
     */
    public static void main(String[] args) {
        testThreadSafety();
    }
}
