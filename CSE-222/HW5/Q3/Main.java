import java.io.File;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        AgeSearchTree<AgeData> ageTree = new AgeSearchTree<>();

        ageTree.add(new AgeData(10));
        ageTree.add(new AgeData(20));
        ageTree.add(new AgeData(5));
        ageTree.add(new AgeData(15));
        ageTree.add(new AgeData(10));

        System.out.println("ageTree.toString() method call: ");
        System.out.println(ageTree);

        System.out.println("ageTree.youngerThan(15) call: ");
        System.out.println(ageTree.youngerThan(15));
        System.out.println("ageTree.olderThan(7) call: ");
        System.out.println(ageTree.olderThan(7));
        System.out.println("ageTree.find(new AgeData(10) call: ");
        System.out.println(ageTree.find(new AgeData(10)));

        System.out.println("Removing 10 and 20...");
        ageTree.remove(new AgeData(10));
        ageTree.remove(new AgeData(20));
        System.out.println(ageTree);
    }
}