import java.io.File;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        MaxHeap heap = new MaxHeap();

        heap.add(new AgeData(10));
        heap.add(new AgeData(5));
        heap.add(new AgeData(70));
        heap.add(new AgeData(10));
        heap.add(new AgeData(50));
        heap.add(new AgeData(5));
        heap.add(new AgeData(15));

        System.out.println("heap.toString() method call: ");
        System.out.println(heap);

        System.out.println("heap.youngerThan(15) call: ");
        System.out.println(heap.youngerThan(15));
        System.out.println("heap.olderThan(7) call: ");
        System.out.println(heap.olderThan(7));
        System.out.println("heap.find(new AgeData(10) call: ");
        System.out.println(heap.find(new AgeData(10)));

        System.out.println("10 and 15 removing...");
        heap.remove(new AgeData(10));
        heap.remove(new AgeData(15));
        System.out.println(heap);
    }
}