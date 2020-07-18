import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        LinkedList <WordLinkedList> list = new LinkedList<>();
        CrossWordPuzzle puzzle = new CrossWordPuzzle(list);
        String choice;
        int val = 0;
        Scanner in = new Scanner(System.in);

        do{
            System.out.println("Please enter the operation number...");
            System.out.println("1) ADD...");
            System.out.println("2) PRINT...");
            System.out.println("0) EXIT");

            choice = in.nextLine();

            try{

                val = Integer.parseInt(choice);

            }catch(Exception e){

                System.out.println("\nInvalid choice...");

            }

            switch (val)
            {
                case 1:
                    puzzle.addWord(whichWord());
                    break;

                case 2:
                    puzzle.print();
                    break;

                case 0:
                    System.out.println("The program turning off...");
                    break;

                default:
                    System.out.println("INVALID CHIOCE...");
                    break;
            }
        }while(val != 0);
    }

    public static String whichWord()
    {
        String str;
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter the word in puzzle...");

        str = in.nextLine();

        return str;
    }
}
