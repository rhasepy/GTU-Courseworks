import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main
{
    public static void main(String[] args) {

        SoftwareStore program = new SoftwareStore(new BinarySearchTree<Software>());
        
        program.run();
    }
}
