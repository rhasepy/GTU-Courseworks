import java.io.Serializable;
import java.util.Scanner;
/**
 * Binary Tree Class implements Serializable
 * @author Muharrem Ozan Yesiller 171044033
 */
public class BinaryTree<E> implements Serializable
{
    /**
     * root node of binary tree
     */
    protected Node<E> root;

    /**
     * private static node class for binary tree data structre
     * @param <E> generic param for instantiate
     */
    protected static class Node<E> implements Serializable
    {
        /**
         * data of node
         */
        protected E data;

        /**
         * left child of node
         */
        protected Node<E> left;

        /**
         * right child of node
         */
        protected Node<E> right;

        /**
         * one parameter consturctor of node
         * @param data generate with this element
         */
        public Node(E data)
        {
            this.data = data;
            left = null;
            right = null;
        }

        /**
         * overriding to string  method
         * @return string of node
         */
        @Override
        public String toString() { return data.toString(); }
    }

    /**
     * no parameter cosntructor binary tree
     */
    public BinaryTree() { root = null; }

    /**
     * one parameter constructor binarytree
     * @param root generate with this node
     */
    protected BinaryTree(Node <E> root) { this.root = root; }

    /**
     * three parameter constructor
     * @param data generate with this element
     * @param leftTree generate with this left child tree
     * @param rightTree generate with this right child tree
     */
    public BinaryTree(E data, BinaryTree <E> leftTree, BinaryTree <E> rightTree)
    {
        root = new Node<E>(data);

        if(leftTree != null)
            root.left = leftTree.root;
        else
            root.left = null;

        if(rightTree != null)
            root.right = rightTree.root;
        else
            root.right = null;
    }

    /**
     * get one sub left tree method
     * @return left sub tree
     */
    public BinaryTree<E> getLeftSubtree()
    {
        if(root != null && root.left != null)
            return new BinaryTree<E>(root.left);

        else
            return null;
    }

    /**
     * get one sub left tree method
     * @return left right tree
     */
    public BinaryTree<E> getRightSubtree()
    {
        if(root != null && root.right != null)
            return new BinaryTree<E>(root.right);

        else
            return null;
    }

    /**
     * get data of local root
     * @return root data
     */
    public E getData() { return root.data; }

    /**
     * is leaf method gives information of root is leaf or not
     * @return true if root is leaf root otherwise false
     */
    public boolean isLeaf() { return (root.left == null) && root.right == null; }

    /**
     * to string method
     * @return string of preorder shape of root
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * traverse pre order (visit root and visit left and visit right recursively)
     * @param node root which user want
     * @param depth depth of local node
     * @param to generate string of node preorder
     */
    public void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
    {
        if(node == null)
            sb.append("");

        else
        {
            sb.append(node.toString());
            //sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * read binary tree with scanner
     * @param jin scanner parameter for building binary tree
     * @return BinaryTree<String> object
     */
    public static BinaryTree<String> readBinaryTree(Scanner jin)
    {
        String data = jin.nextLine().trim();

        if(data.equals("null"))
            return null;

        else
        {
            BinaryTree<String> leftTree = readBinaryTree(jin);
            BinaryTree<String> rightTree = readBinaryTree(jin);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }
}
