import java.util.Scanner;
import java.util.Stack;

/**
 * Expression tree class represent BinaryTree<String>
 * @author Muharrem Ozan Yesiller 171044033
 */
public class ExpressionTree extends BinaryTree<String>
{
    /**
     * Give information of the builder expression is prefix
     * It is static because the class use data in our static method
     */
    static private boolean prefix = false;

    /**
     * Give information of the builder expression is postfix
     * It is static because the class use data in our static method
     */
    static private boolean postfix = false;

    /**
     * One parameter constructor
     * The constructor understands the item is prefix or postfix
     * And then update prefix and postfix field
     * If the expression is postfix, It change expression instead of reverse of this expression
     * @param args to build expression
     */
    public ExpressionTree(String args)
    {
        prefix = isPrefix(args);
        postfix = isPostfix(args);

        if(postfix)
            args = reverse(args.split("\\s+"));

        else if(!prefix)
            throw new IllegalArgumentException("The argument is not postfix or prefix...");

        try {
            root = ExpressionTree.readBinaryTree( new Scanner(args) ).root;
        } catch (Exception e) {
            // if the read Binary tree throw nullpointer exception, expression is not prefix or postfix
            throw new IllegalArgumentException("The argument is not postfix or prefix expression...");
        }
    }

    /**
     * private static helper method
     * @param args expression splitted space by space
     * @return String of reverse of splitted string
     */
    private static String reverse(String[] args)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = args.length - 1 ; i >= 0 ; --i)
        {
            sb.append(args[i]);
            if(i != 0)
                sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * Guess expression is prefix or not
     * If the prediction is not exactly correct,
     * if the statement is not correct, an exception will be thrown when reading the expression.
     * @param item expression
     * @return true if expression may be prefix otherwise false
     */
    private static boolean isPrefix(String item)
    {
        String[] temp = item.split("\\s+");
        return isOperator(temp[0]);
    }

    /**
     * Guess expression is postfix or not
     * If the prediction is not exactly correct,
     * if the statement is not correct, an exception will be thrown when reading the expression.
     * @param item expression
     * @return true if expression may be postfix otherwise false
     */
    private static boolean isPostfix(String item)
    {
        String[] temp = item.split("\\s+");

        return isOperand(temp[0]);
    }

    /**
     * Read binary method
     * @param jin scanner object filled in expression
     * @return built binary tree with expression elements
     */
    public static BinaryTree<String> readBinaryTree(Scanner jin)
    {
        try
        {
            String data = jin.next();

            if (isOperand(data))
                return new BinaryTree<>(data, null, null);

            else
            {
                if(!isOperator(data))
                    throw new IllegalArgumentException("The argument is not postfix or prefix expression...");

                /*
                       it depends on the postfix or prefix marrow of the expression.
                       Because the postfix expression is already inverted,
                       but the operands do not change, so the opposite of the process with prefix is applied.
                 */

                if(prefix)
                {
                    BinaryTree<String> leftTree = ExpressionTree.readBinaryTree(jin);
                    BinaryTree<String> rightTree = ExpressionTree.readBinaryTree(jin);
                    return new BinaryTree<>(data, leftTree, rightTree);
                }

                else
                {
                    BinaryTree<String> rightTree = ExpressionTree.readBinaryTree(jin);
                    BinaryTree<String> leftTree = ExpressionTree.readBinaryTree(jin);
                    return new BinaryTree<>(data, leftTree, rightTree);
                }
            }
        }
        catch (Exception e) { return null; }
        // if the last element expression is null(e x p r e s s i o n \0)
    }

    /**
     * helper method
     * @param x element to recognation operator or not
     * @return x is operator then return true otherwise return false
     */
    private static boolean isOperator(String x)
    {
        if(x.length() != 1)
            return false;

        return x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/");
    }

    /**
     * helper method
     * @param x element to recognation operand or not
     * @return x is operand then return true otherwise return false
     */
    private static boolean isOperand(String x)
    {
        try{
            Integer.parseInt(x);
            return true;
        } catch (Exception e) { return false; }
    }

    /**
     * evaluate tree value
     * @return evaluated value of tree
     */
    public int eval() { return eval(root); }

    /**
     * Evalueate tree recursively
     * @param localRoot to be calculate OperatorRoot(leftchild, rightchild)
     * @return evaluated value of tree
     */
    private int eval(BinaryTree.Node<String> localRoot)
    {
        if(isOperand(localRoot.data))
            return Integer.parseInt(localRoot.data.toString());

        else
        {
            int l_val = eval(localRoot.left);
            int r_val = eval(localRoot.right);
            int val = 0;

            switch (localRoot.data)
            {
                case "+":
                    val = l_val + r_val;
                    break;
                case "-":
                    val = l_val - r_val;
                    break;
                case "*":
                    val = l_val * r_val;
                    break;
                case "/":
                    val = l_val / r_val;
                    break;
            }

            return val;
        }
    }

    /**
     * traverse post order (visit root and visit left and visit right recursively)
     * @param node root which user want
     * @param depth depth of local node
     * @param sb generate string of node postorder
     */
    private void postOrderTraverse(Node<String> node, int depth, StringBuilder sb)
    {
        if(node == null)
            sb.append("");

        else
        {
            postOrderTraverse(node.left, depth + 1, sb);
            postOrderTraverse(node.right, depth + 1, sb);

            sb.append(node.toString());
            sb.append("\n");
        }
    }

    /**
     * to string2 method
     * @return string of postorder shape of root
     */
    public String toString2()
    {
        StringBuilder sb = new StringBuilder();
        postOrderTraverse(root, 1, sb);
        return sb.toString();
    }
}