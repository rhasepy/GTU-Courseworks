
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
public class Main {
  private static BinarySearchTree<Integer> test_bst =  new BinarySearchTree<>();

  private static RedBlackTree<Integer> test_rb_tree =  new RedBlackTree<>();

  private static TreeSet<Integer> test_rb_java =  new TreeSet<>();

  private static BTree<Integer> test_btree =  new BTree<>(3);

  private static Skiplist<Integer> test_sList =   new Skiplist<>();

  private static ConcurrentSkipListSet<Integer> test_sList_java =  new ConcurrentSkipListSet<>();

  private static MySkipList<Integer> test_sList_q2 =  new MySkipList<>();

  public static void main(String[] args)
  {

        analysis_tree_add(10000);
        System.out.println();
        analysis_tree_add(10);
        System.out.println();
        analysis_tree_add(20000);
        System.out.println();
        analysis_tree_add(10);
        System.out.println();
        analysis_tree_add(40000);
        System.out.println();
        analysis_tree_add(10);
        System.out.println();
        analysis_tree_add(80000);
        System.out.println();
        analysis_tree_access_each_data();
        System.out.println();
        analysis_tree_remove(10);
  }

  public static void analysis_tree_remove(int size)
  {

        long[] time_bst = new long[10];
        long[] time_rb = new long[10];
        long[] time_rb_java = new long[10];
        long[] time_btree = new long[10];
        long[] time_slist = new long[10];
        long[] time_slist_java = new long[10];
        long[] time_slist_q2 = new long[10];

        long s;
        long f;

        for(int i = 0 ; i < size ; ++i) {

            s = System.nanoTime();
            test_bst.remove(test_bst.getData());
            f = System.nanoTime();
            time_bst[i] = (f-s);

            s = System.nanoTime();
            test_rb_tree.delete(test_rb_tree.getData());
            f = System.nanoTime();
            time_rb[i] = (f-s);

            s = System.nanoTime();
            test_rb_java.remove(test_rb_java.pollFirst());
            f = System.nanoTime();
            time_rb_java[i] = (f-s);

            s = System.nanoTime();
            //test_btree.remove(test_btree.getFirst());
            f = System.nanoTime();
            time_btree[i] = (f-s);

            s = System.nanoTime();
            test_sList.remove(test_sList.getFirst());
            f = System.nanoTime();
            time_slist[i] = (f-s);

            s = System.nanoTime();
            test_sList_java.remove(test_sList_java.first());
            f = System.nanoTime();
            time_slist_java[i] = (f-s);
        }

        long av_bst = 0;
        long av_rb = 0;
        long av_rb_java = 0;
        long av_btree = 0;
        long av_slist = 0;
        long av_slist_java = 0;

        for(int i = 0 ; i < 10 ; ++i) {
            av_bst += (time_bst[i]/10);
            av_rb += (time_rb[i]/10);
            av_rb_java += (time_rb_java[i]/10);
            av_btree += (time_btree[i]/10);
            av_slist += (time_slist[i]/10);
            av_slist_java += (time_slist_java[i]/10);
        }

        System.out.println("Analysis remove 10 data from trees");
        System.out.println("avarage bst: " + av_bst);
        System.out.println("avarage red black tree: " + av_rb);
        System.out.println("avarage red black tree java: " + av_rb_java);
        System.out.println("avarage btree: " + av_btree);
        System.out.println("avarage skiplist: " + av_slist);
        System.out.println("avarage skiplist java: " + av_slist_java);
  }

  public static void analysis_tree_access_each_data()
  {
        long[] time_bst = new long[10];
        long[] time_rb = new long[10];
        long[] time_rb_java = new long[10];
        long[] time_btree = new long[10];
        long[] time_slist = new long[10];
        long[] time_slist_java = new long[10];
        long[] time_slist_q2 = new long[10];

        Random rand = new Random();
        long s;
        long f;

        for(int i = 0 ; i < 10 ; ++i) {

                Integer item = (Integer) rand.nextInt();

                s = System.nanoTime();
                test_bst.toString();
                f = System.nanoTime();
                time_bst[i] = (f-s);

                s = System.nanoTime();
                test_rb_tree.toString();
                f = System.nanoTime();
                time_rb[i] = (f-s);

                s = System.nanoTime();
                test_rb_java.toString();
                f = System.nanoTime();
                time_rb_java[i] = (f-s);

                s = System.nanoTime();
                test_btree.toString();
                f = System.nanoTime();
                time_btree[i] = (f-s);

                s = System.nanoTime();
                test_sList.toString();
                f = System.nanoTime();
                time_slist[i] = (f-s);

                s = System.nanoTime();
                test_sList_java.toString();
                f = System.nanoTime();
                time_slist_java[i] = (f-s);
        }

        long av_bst = 0;
        long av_rb = 0;
        long av_rb_java = 0;
        long av_btree = 0;
        long av_slist = 0;
        long av_slist_java = 0;

        for(int i = 0 ; i < 10 ; ++i) {
            av_bst += (time_bst[i]/10);
            av_rb += (time_rb[i]/10);
            av_rb_java += (time_rb_java[i]/10);
            av_btree += (time_btree[i]/10);
            av_slist += (time_slist[i]/10);
            av_slist_java += (time_slist_java[i]/10);
        }

        System.out.println("Analysis access data to trees");
        System.out.println("avarage bst: " + av_bst);
        System.out.println("avarage red black tree: " + av_rb);
        System.out.println("avarage red black tree java: " + av_rb_java);
        System.out.println("avarage btree: " + av_btree);
        System.out.println("avarage skiplist: " + av_slist);
        System.out.println("avarage skiplist java: " + av_slist_java);
  }

  public static void analysis_tree_add(int size)
  {

        long[] time_bst = new long[10];
        long[] time_rb = new long[10];
        long[] time_rb_java = new long[10];
        long[] time_btree = new long[10];
        long[] time_slist = new long[10];
        long[] time_slist_java = new long[10];
        long[] time_slist_q2 = new long[10];

        Random rand = new Random();
        long s;
        long f;

        for(int i = 0 ; i < 10 ; ++i) {

            for(int j = 0 ; j < size ; ++j)
            {
                Integer item = (Integer) rand.nextInt();

                s = System.nanoTime();
                test_bst.add(item);
                f = System.nanoTime();
                time_bst[i] = (f-s);

                s = System.nanoTime();
                test_rb_tree.add(item);
                f = System.nanoTime();
                time_rb[i] = (f-s);

                s = System.nanoTime();
                test_rb_java.add(item);
                f = System.nanoTime();
                time_rb_java[i] = (f-s);

                s = System.nanoTime();
                test_btree.add(item);
                f = System.nanoTime();
                time_btree[i] = (f-s);

                s = System.nanoTime();
                test_sList.add(item);
                f = System.nanoTime();
                time_slist[i] = (f-s);

                s = System.nanoTime();
                test_sList_java.add(item);
                f = System.nanoTime();
                time_slist_java[i] = (f-s);
            }
        }

        long av_bst = 0;
        long av_rb = 0;
        long av_rb_java = 0;
        long av_btree = 0;
        long av_slist = 0;
        long av_slist_java = 0;

        for(int i = 0 ; i < 10 ; ++i) {
            av_bst += (time_bst[i]/10);
            av_rb += (time_rb[i]/10);
            av_rb_java += (time_rb_java[i]/10);
            av_btree += (time_btree[i]/10);
            av_slist += (time_slist[i]/10);
            av_slist_java += (time_slist_java[i]/10);
        }

        System.out.println("Analysis adding to trees (size -> " + size + ")");
        System.out.println("avarage bst: " + av_bst);
        System.out.println("avarage red black tree: " + av_rb);
        System.out.println("avarage red black tree java: " + av_rb_java);
        System.out.println("avarage btree: " + av_btree);
        System.out.println("avarage skiplist: " + av_slist);
        System.out.println("avarage skiplist java: " + av_slist_java);
  }

}
