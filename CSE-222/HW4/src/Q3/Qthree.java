import java.util.NoSuchElementException;
import java.util.Stack;

public class Qthree
{
    public static void main(String[] args)
    {
        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("******************************************************");
        System.out.println("QUESTION 3 PART 1 TEST: \n");
        System.out.println("1) " + "this function writes the sentence in reverse");
        reverse("this function writes the sentence in reverse", 0);
        System.out.println("\n2) " + "CSE222 Data Structures And Algorithms");
        reverse("CSE222 Data Structures And Algorithms", 0);
        System.out.println("\n3) " + "this interface does not provide support for indexed access to elements");
        reverse("this interface does not provide support for indexed access to elements", 0);
        System.out.println("\nEnd of the test\n");
        /////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("******************************************************");
        System.out.println("QUESTION 3 PART 2 TEST: \n");

        System.out.print("1) elfish");
        if(isElfish("elfish"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");


        System.out.print("2) pencil");
        if(isElfish("pencil"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");


        System.out.print("3) whiteleaf");
        if(isElfish("whiteleaf"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");


        System.out.print("4) freq");
        if(isElfish("freq"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");


        System.out.print("5) tasteful");
        if(isElfish("tasteful"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");


        System.out.print("6) rampage");
        if(isElfish("rampage"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");


        System.out.print("7) unfriendly");
        if(isElfish("elfish"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");


        System.out.print("8) waffles");
        if(isElfish("waffles"))
            System.out.println(" is 'elfish'...");
        else
            System.out.println(" is not 'elfish'...");

        System.out.println("\nEnd of the test\n");
        /////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("******************************************************");
        System.out.println("QUESTION 3 PART 3 TEST: \n");

        int[] unsorted = { 64, 25, 12, 22, 11 };
        System.out.println("1)The array is: ");
        for (int value : unsorted) System.out.print(value + " ");
        selection_sort(unsorted);
        System.out.println("Sorted...\nResult: ");
        for (int value : unsorted) System.out.print(value + " ");
        System.out.println("");


        int[] unsorted2 = { 12, 11, 13, 5, 6 };
        System.out.println("\n2)The array is: ");
        for (int value : unsorted2) System.out.print(value + " ");
        selection_sort(unsorted2);
        System.out.println("Sorted...\nResult: ");
        for (int value : unsorted2) System.out.print(value + " ");
        System.out.println("");


        int[] unsorted3 = { -5, -10, 0, -3, 8, 5, -1, 10 };
        System.out.println("\n3)The array is: ");
        for (int value : unsorted3) System.out.print(value + " ");
        selection_sort(unsorted3);
        System.out.println("Sorted...\nResult: ");
        for (int value : unsorted3) System.out.print(value + " ");
        System.out.println("");


        int[] unsorted4 = { 40, 60, 1, 200, 9, 83, 17 ,-5, -10, 0, -3, 8, 5, -1, 10 };
        System.out.println("\n4)The array is: ");
        for (int value : unsorted4) System.out.print(value + " ");
        selection_sort(unsorted4);
        System.out.println("Sorted...\nResult: ");
        for (int value : unsorted4) System.out.print(value + " ");
        System.out.println("");

        System.out.println("\nEnd of the test\n");
        /////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("******************************************************");
        System.out.println("QUESTION 3 PART 4 AND 5 TEST: \n");

        System.out.println("\nPostfix expression: " + "10 50 5 2 * - 10 / + 2 + 21 7 / -");
        System.out.println("Result: " + evaluatePostfix("10 50 5 2 * - 10 / + 2 + 21 7 / -"));

        System.out.println("\nPostfix expression: " + "8 2 * 3 * 3 - 8 4 / 1 1 + / +");
        System.out.println("Result: " + evaluatePostfix("8 2 * 3 * 3 - 8 4 / 1 1 + / +"));

        System.out.println("\nPostfix expression: " + "2 3 1 * + 9 -");
        System.out.println("Result: " + evaluatePostfix("2 3 1 * + 9 -"));

        try{
            System.out.println("\nPostfix expression: " + "12 2 + -");
            System.out.println("Result: " + evaluatePostfix("12 2 + -"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("This is a not postfix expression!!!");
        }

        System.out.println("\nPrefix expression: " + "- + * 2 3 * 5 4 9");
        System.out.println("Result: " + evaluatePrefix("- + * 2 3 * 5 4 9"));

        System.out.println("\nPrefix expression: " + "- + + 10 / - 50 * 5 2 10 2 / 21 7");
        System.out.println("Result: " + evaluatePrefix("- + + 10 / - 50 * 5 2 10 2 / 21 7"));

        System.out.println("\nPrefix expression: " + "+ - * 2 2 / 16 8 5");
        System.out.println("Result: " + evaluatePrefix("+ - * 2 2 / 16 8 5"));

        try{
            System.out.println("\nPrefix expression: " + "- 10 2 +");
            System.out.println("Result: " + evaluatePrefix("- 10 2 +"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("This is a not prefix expression!!!");
        }

        System.out.println("\nEnd of the test\n");
        /////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////
        System.out.println("******************************************************");
        System.out.println("QUESTION 3 PART 6 TEST: \n");

        int[][] ar = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };

        for(int i = 0 ; i < ar.length ; ++i)
        {
            for(int j = 0 ; j < ar[i].length ; ++j) {
                if(ar[i][j] >= 10 || ar[i][j] <= -10)
                    System.out.print(ar[i][j] + " ");
                else
                    System.out.print(ar[i][j] + "  ");
            }
            System.out.println("");
        }

        System.out.print("\nSpiral of this array: ");
        spiral(ar);
        System.out.println("\n");


        int[][] ar2 = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
                {17,18,19,20}
        };

        for(int i = 0 ; i < ar2.length ; ++i)
        {
            for(int j = 0 ; j < ar2[i].length ; ++j) {
                if(ar2[i][j] >= 10 || ar2[i][j] <= -10)
                    System.out.print(ar2[i][j] + " ");
                else
                    System.out.print(ar2[i][j] + "  ");
            }
            System.out.println("");
        }

        System.out.print("\nSpiral of this array: ");
        spiral(ar2);
        System.out.println("\n");

        int ar3[][] = { { 1, 2, 3, 4, 5, 6 },
                { 7, 8, 9, 10, 11, 12 },
                { 13, 14, 15, 16, 17, 18 } };

        for(int i = 0 ; i < ar3.length ; ++i)
        {
            for(int j = 0 ; j < ar3[i].length ; ++j) {
                if(ar3[i][j] >= 10 || ar3[i][j] <= -10)
                    System.out.print(ar3[i][j] + " ");
                else
                    System.out.print(ar3[i][j] + "  ");
            }

            System.out.println("");
        }

        System.out.print("\nSpiral of this array: ");
        spiral(ar2);
        System.out.println("\n");

        System.out.println("\nEnd of the test");
        System.out.println("******************************************************");
        /////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * The algorithm of the prefix expression in reverse is considered.
     * so I created a StringBuilder and filled the expression in reverse in a recursive method.
     * @param expression, prefix expression
     * @return result of prefix expression
     */
    public static int evaluatePrefix(String expression)
    {
        StringBuilder sb = new StringBuilder();
        reverseExpression(expression, 0, sb);
        return evalPrefix(sb.toString(), 0, new Stack<Integer>());
    }

    /**
     * StringBuilder is filled with the reverse of the expression
     * @param item, prefix expression
     * @param index, index to be processed
     * @param sb, StringBuilder to fill
     */
    private static void reverseExpression(String item, int index, StringBuilder sb)
    {
        if(index >= item.length())
            return;

        else
        {
            StringBuilder temp = new StringBuilder();

            index = splitterReverse(item, index, temp);

            reverseExpression(item, index+1, temp);
            sb.insert(0, temp.toString());
        }
    }

    /**
     * parses the expression we get the opposite as an element to be processed or processed.
     * @param item, reverse prefix expression
     * @param index, index to be processed
     * @param sb, StringBuilder to fill
     * @return which index the function is in
     */
    private static int splitterReverse(String item, int index, StringBuilder sb)
    {
        if(index >= item.length()) {
            sb.append(" ");
            return index;
        }

        if(item.charAt(index) == ' ') {
            sb.append(" ");
            return index;
        }

        else
        {
            sb.append(item.charAt(index));
            return splitterReverse(item, index + 1, sb);
        }
    }

    /**
     * main method evaluating prefix expression
     * @param exp, prefix expression
     * @param index, which index the function is in
     * @param operandStack, stack of operand
     * @return value of prefix expression
     */
    private static int evalPrefix(String exp, int index, Stack<Integer> operandStack)
    {
        if(index >= exp.length())
        {
            if(operandStack.size() != 1)
                throw new IllegalArgumentException("This is not a prefix expression...");

            return operandStack.peek();
        }

        else
        {
            StringBuilder sb = new StringBuilder();

            index = splitter(exp, index, sb);

            if(isOperand(sb.toString()))
                operandStack.push(Integer.parseInt(sb.toString()));

            else if(isOperator(sb.toString()))
                operandStack.push(evalOp_prefix(sb.toString().charAt(0), operandStack));

            return evalPrefix(exp, index+1, operandStack);
        }
    }

    /**
     * Pop stack, do operation and push stack
     * @param op, operator
     * @param operandStack, operand stack
     * @return result of operation
     */
    private static int evalOp_prefix(char op, Stack<Integer> operandStack)
    {
        int leftOperand = operandStack.pop();
        int rightOperand = operandStack.pop();
        int result = 0;

        switch (op)
        {
            case '+':
                result = (leftOperand + rightOperand);
                break;

            case '-':
                result = (leftOperand - rightOperand);
                break;

            case '*':
                result = (leftOperand * rightOperand);
                break;

            case '/':
                result = (leftOperand / rightOperand);
                break;
        }

        return result;
    }


    /**
     * method designed for the user to use comfortably
     * @param expression, prefix expression as input
     * @return result of prefix expression
     */
    public static int evaluatePostfix(String expression)
    {
        return evalPostfix(expression, 0, new Stack<Integer>());
    }

    /**
     *
     * @param exp, postfix expression
     * @param index index of postfix, should start 0
     * @param operandStack stack of operand
     * @return value of postfix
     */
    private static int evalPostfix(String exp, int index, Stack<Integer> operandStack)
    {
        if(index >= exp.length())
        {
            if(operandStack.size() != 1)
                throw new IllegalArgumentException("This is not a prefix expression...");

            return operandStack.peek();
        }

        else
        {
            StringBuilder sb = new StringBuilder();

            index = splitter(exp, index, sb);

            if(isOperand(sb.toString()))
                operandStack.push(Integer.parseInt(sb.toString()));

            else if(isOperator(sb.toString()))
                operandStack.push(evalOp_postfix(sb.toString().charAt(0), operandStack));

            return evalPostfix(exp, index+1, operandStack);
        }
    }

    /**
     * parses the expression part part
     * @param item, postfix expression
     * @param index, index of part
     * @param sb, part
     * @return string of part
     */
    private static int splitter(String item, int index, StringBuilder sb)
    {
        if(index == item.length())
            return index;

        if(item.charAt(index) == ' ')
            return index;

        else
        {
            sb.append(item.charAt(index));
            return splitter(item, index+1, sb);
        }
    }

    /**
     * pop stack, operate it and push stack
     * @param op operator
     * @param operandStack stack of operand
     * @return value of operation
     */
    private static int evalOp_postfix(char op, Stack<Integer> operandStack)
    {
        int rightOperand = operandStack.pop();
        int leftOperand = operandStack.pop();
        int result = 0;

        switch (op)
        {
            case '+':
                result = (leftOperand + rightOperand);
                break;

            case '-':
                result = (leftOperand - rightOperand);
                break;

            case '*':
                result = (leftOperand * rightOperand);
                break;

            case '/':
                result = (leftOperand / rightOperand);
                break;
        }

        return result;
    }

    /**
     * is operator test
     * @param item, item
     * @return true, item is operator
     * @return false, item is not operator
     */
    private static boolean isOperator(String item)
    {
        if(item.length() > 1)
            return false;

        else
            return item.equals("/") || item.equals("*") || item.equals("-") || item.equals("+");
    }

    /**
     * is operand test
     * @param item, item
     * @return true, item is operand
     * @return false, item is not operand
     */
    private static boolean isOperand(String item)
    {
        try{
            Integer.parseInt(item);
            return true;
        }catch (NumberFormatException e){
            return  false;
        }
    }

    /**
     * spiral print method
     * @param arr input array will print spirally
     */
    public static void spiral(int[][] arr)
    {
        print_spiral(arr, 0, 0, arr.length, arr[0].length, 0, arr.length*arr[0].length);
    }

    /**
     * @param arr, array to be printed spiral
     * @param index_i, starting position i
     * @param index_j, starting position j
     * @param size_i, size of row
     * @param size_j, size of column
     * @param count, how many elements will be done for each spiral prints
     * @param size number of elements in array as size_i*size_j
     */
    private static void print_spiral(int[][] arr, int index_i, int index_j, int size_i, int size_j, int count, int size)
    {
        if(count == size)
            return;

        else
        {
            print_RIGHT(arr, index_i, index_j, size_j);
            count += size_j;

            if(count == size)
                return;

            print_DOWN(arr, index_i+1, index_j+size_j-1, size_i);
            count += size_i-1;

            if(count == size)
                return;

            print_LEFT(arr, index_i+size_i-1, index_j, size_j-1);
            count += size_j-1;

            if(count == size)
                return;

            print_UP(arr, index_i+1, index_j, size_i-1);
            count += size_i-2;

            print_spiral(arr, index_i+1, index_j+1, size_i-2, size_j-2, count, size);
        }
    }

    /**
     * prints by going to the right from the given location
     * @param arr array to be want to spiral print
     * @param index_i, initial index i
     * @param index_j, initial index j
     * @param size_j, how far to go right
     */
    private static void print_RIGHT(int[][] arr, int index_i,int index_j, int size_j)
    {
        if(arr[index_i].length == index_j)
            return;

        if(index_j > size_j)
            return;

        else
        {
            System.out.print(arr[index_i][index_j] + " ");
            print_RIGHT(arr, index_i,index_j+1, size_j);
        }
    }

    /**
     * prints by going to the left from the given location
     * The algorithm followed here is actually similar to printRight,
     * but the reverse of this algorithm is suppressed.
     * @param arr array to be want to spiral print
     * @param index_i, initial index i
     * @param index_j, initial index j
     * @param size_j, how far to go left
     */
    private static void print_LEFT(int[][] arr, int index_i, int index_j, int size_j)
    {
        if(arr[index_i].length-1 == index_j)
            return;

        if(index_j >= size_j){
            System.out.print(arr[index_i][index_j] + " ");
            return;
        }

        else
        {
            print_LEFT(arr, index_i, index_j+1, size_j);
            System.out.print(arr[index_i][index_j] + " ");
        }
    }

    /**
     * prints by going to the up from the given location
     * The algorithm followed here is actually similar to printDown,
     * but the reverse of this algorithm is suppressed.
     * @param arr array to be want to spiral print
     * @param index_i, initial index i
     * @param index_j, initial index j
     * @param size_i, how far to go up
     */
    private static void print_UP(int[][] arr, int index_i, int index_j, int size_i)
    {
        if(arr.length-1 == index_i)
            return;

        if(index_i >= size_i){
            System.out.print(arr[index_i][index_j] + " ");
            return;
        }

        else
        {
            print_UP(arr, index_i+1, index_j, size_i);
            System.out.print(arr[index_i][index_j] + " ");
        }
    }

    /**
     * prints by going to the down from the given location
     * @param arr array to be want to spiral print
     * @param index_i, initial index i
     * @param index_j, initial index j
     * @param size_i, how far to go down
     */
    private static void print_DOWN(int[][] arr, int index_i, int index_j, int size_i)
    {
        if(arr.length == index_i)
            return;

        if(index_i > size_i)
            return;

        else
        {
            System.out.print(arr[index_i][index_j] + " ");
            print_DOWN(arr, index_i+1, index_j, size_i);
        }
    }

    /**
     * East to use method
     * @param arr input array to be want to sort
     */
    public static void selection_sort(int[] arr) { traverseOn_array(arr, 0); }

    /**
     * traverse on array element by element
     * @param arr input array to be want to sort
     * @param index initial index
     */
    private static void traverseOn_array(int[] arr, int index)
    {
        if(index == arr.length - 1)
            return;

        else
        {
            swap(arr, index, find_minIndex(arr, index + 1, index));
            traverseOn_array(arr, index + 1);
        }
    }

    /**
     * Checking all the items after that item on each item.
     * And find min value index
     * @param arr input array to be want to sort
     * @param index from which index
     * @param min_idx minimum index (previous or current)
     * @return minimum index according to index th element
     */
    private static int find_minIndex(int[] arr, int index, int min_idx)
    {
        if(index == arr.length)
            return min_idx;

        else
        {
            if(arr[index] < arr[min_idx])
                min_idx = index;

            return  find_minIndex(arr, index+1, min_idx);
        }
    }

    /**
     * basic swap each other value in input array
     * @param arr input array
     * @param i swapping element 1
     * @param ii swapping element 2
     */
    private static void swap(int[] arr, int i, int ii)
    {
        int temp = arr[i];
        arr[i] = arr[ii];
        arr[ii] = temp;
    }

    /**
     * East to use method
     * @param word input word
     * @return true word is elfish word
     * @return false word is not elfish word
     */
    public static boolean isElfish(String word)
    {
        return testElfish(word, 0, false ,false, false);
    }

    /**
     * Test elfish method
     * @param word input word
     * @param index index to be processed
     * @param count_e has word e or E
     * @param count_l  has word  l or L
     * @param count_f has word f or F
     * @return true word is elfish
     * @return false word is not elfish
     */
    public static boolean testElfish(String word, int index, boolean count_e, boolean count_l, boolean count_f)
    {
        if(word.length() == index)
            return (count_e && count_l && count_f);

        else
        {
            if(word.charAt(index) == 'e' || word.charAt(index) == 'E')
                count_e = true;
            else if(word.charAt(index) == 'l' || word.charAt(index) == 'L')
                count_l = true;
            else if(word.charAt(index) == 'f' || word.charAt(index) == 'F')
                count_f = true;

            return testElfish(word, index+1, count_e, count_l, count_f);
        }
    }

    /**
     * prints the sentence in reverse word by word not letter by letter
     * @param sentence input string must be sentence
     * @param index initial index
     */
    public static void reverse(String sentence, int index)
    {
        if(index >= sentence.length())
            return;

        else
        {
            StringBuilder sb_temp = new StringBuilder();

            if(index == 0)
                reverse(sentence, word(sentence, sb_temp, index, true) + 1);
            else
                reverse(sentence, word(sentence, sb_temp, index, false) + 1);

            System.out.print(sb_temp.toString());
        }
    }

    /**
     * pulls from sentence to word and appends to StringBuilder
     * @param sentence input sentence
     * @param sb Character will be filled in and a word will be created
     * @param current_i current index
     * @param backSlashFlag is last element of expression or not
     * @return the last index of received word
     */
    private static int word(String sentence, StringBuilder sb, int current_i, boolean backSlashFlag)
    {
        if(current_i == sentence.length()) {

            if(backSlashFlag)
                sb.append("\n");
            else
                sb.append(" ");

            return current_i;
        }

        if(sentence.charAt(current_i) == ' ') {

            if(backSlashFlag)
                sb.append("\n");
            else
                sb.append(sentence.charAt(current_i));

            return current_i;
        }

        else
        {
            sb.append(sentence.charAt(current_i));
            return word(sentence, sb, current_i+1, backSlashFlag);
        }
    }
}
