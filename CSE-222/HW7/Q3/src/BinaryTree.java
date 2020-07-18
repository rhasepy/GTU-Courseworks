
import java.io.Serializable;
import java.util.Scanner;
public class BinaryTree<E> implements Serializable {
  protected static class Node<E> implements Serializable {
    protected E data;

    protected BinaryTree.Node<E> left;

    protected BinaryTree.Node<E> right;

    public Node(E data) {
            this.data = data;
            left = null;
            right = null;
    }

    @Override
    public String toString() {
 return data.toString();
    }

  }

  protected BinaryTree.Node<E> root;

  public BinaryTree() {
 root = null;
  }

  protected BinaryTree(BinaryTree.Node<E> root) {
 this.root = root;
  }

  public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
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

  public BinaryTree<E> getLeftSubtree() {
        if(root != null && root.left != null)
            return new BinaryTree<E>(root.left);

        else
            return null;
  }

  public BinaryTree<E> getRightSubtree() {
        if(root != null && root.right != null)
            return new BinaryTree<E>(root.right);

        else
            return null;
  }

  public E getData() {
 return root.data;
  }

  public boolean isLeaf() {
 return (root.left == null) && root.right == null;
  }

  @Override
  public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
  }

  private void preOrderTraverse(BinaryTree.Node<E> node, int depth, StringBuilder sb) {
        //for(int i = 0 ; i < depth ; ++i)
            //sb.append("  ");

        if(node == null)
            sb.append("");//("null\n");
        else
        {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
  }

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
