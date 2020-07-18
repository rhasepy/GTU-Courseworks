import java.io.File;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        ExpressionTree expTree = new ExpressionTree("+ + 10 * 5 15 20");
        ExpressionTree expTree2 = new ExpressionTree("10 5 15 * + 20 +");

        System.out.println("First expression tree");
        System.out.println(expTree);
        System.out.println("----------------------");
        System.out.println("Second expression tree");
        System.out.println(expTree2);

        System.out.println("Post order traverse first expression tree");
        System.out.println(expTree.toString2());
        System.out.println("--------------------------");
        System.out.println("Post order traverse second expiression tree");
        System.out.println(expTree2.toString2());

        System.out.println("result1: " + expTree.eval());
        System.out.println("result2: " + expTree2.eval());
    }
}