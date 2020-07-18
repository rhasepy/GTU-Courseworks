
public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
  /**
   * rotote right tree
   * @param root will be rotate
   * @return new root
   */
  protected BinaryTree.Node<E> rotateRight(BinaryTree.Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;

        return temp;
  }

  /**
   * rotate left
   * @param root will be rotate
   * @return new root
   */
  protected BinaryTree.Node<E> rotateLeft(BinaryTree.Node<E> root) {
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;

        return temp;
  }

}
