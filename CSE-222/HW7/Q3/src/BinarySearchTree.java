
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {
  protected boolean addReturn;

  protected E deleteReturn;

  @Override
  public boolean add(E item) {
        root = add(root, item);
        return addReturn;
  }

  private BinaryTree.Node<E> add(BinaryTree.Node<E> localRoot, E item) {
        if(localRoot == null) {
            addReturn = true;
            return new Node<E>(item);

        } else if(item.compareTo(localRoot.data) == 0) {

            addReturn = false;
            return localRoot;

        } else if(item.compareTo(localRoot.data) < 0) {

            localRoot.left = add(localRoot.left, item);
            return localRoot;

        } else {

            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
  }

  @Override
  public boolean contains(E target) {
 return (find(target) != null);
  }

  @Override
  public E find(E target) {
 return find(root, target);
  }

  private E find(BinaryTree.Node<E> localRoot, E target) {
        if(localRoot == null)
            return null;

        int compResult = target.compareTo(localRoot.data);

        if(compResult == 0)
            return localRoot.data;

        else if(compResult < 0)
            return find(localRoot.left, target);

        else
            return find(localRoot.right, target);
  }

  @Override
  public E delete(E target) {
       root = delete(root, target);
       return deleteReturn;
  }

  private BinaryTree.Node<E> delete(BinaryTree.Node<E> localRoot, E target) {
        if(localRoot == null)
        {
            deleteReturn = null;
            return localRoot;
        }

        int compRes = target.compareTo(localRoot.data);

        if(compRes < 0) {

            localRoot.left = delete(localRoot.left, target);
            return localRoot;

        } else if(compRes > 0) {

            localRoot.right = delete(localRoot.right, target);
            return localRoot;

        } else {
            deleteReturn = localRoot.data;

            if(localRoot.left == null)
                return localRoot.right;
            else if(localRoot.right == null)
                return localRoot.left;
            else
            {
                if(localRoot.left.right == null)
                {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                }

                else
                {
                    localRoot.data =  findLargestChild(localRoot.left);
                    // solundan en buyuk datayı al ki her şey o datanın solunda kalabilsin...
                    return localRoot;
                }
            }
        }
  }

  private E findLargestChild(BinaryTree.Node<E> parent) {
        if(parent.right.right == null) {

            E returnVal = parent.right.data;
            parent.right = parent.right.left;
            return returnVal;

        } else {
            return findLargestChild(parent.right);
        }
  }

  @Override
  public boolean remove(E target) {
        root = delete(root, target);
        return deleteReturn != null;
  }

  @Override
  public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrderTraverse(root, 1, sb);
        return sb.toString();
  }

  private void inOrderTraverse(BinaryTree.Node<E> node, int depth, StringBuilder sb) {
        if(node == null)
            sb.append("");
        else
        {
            inOrderTraverse(node.left, depth + 1, sb);
            sb.append(node.toString());
            sb.append("\n");
            inOrderTraverse(node.right, depth + 1, sb);
        }
  }

}
