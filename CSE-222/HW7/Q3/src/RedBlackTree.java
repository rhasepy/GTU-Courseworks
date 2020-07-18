
@SuppressWarnings("serial")
public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
  private boolean addReturn;

  private static class RedBlackNode<E> extends BinaryTree.Node<E> {
    private boolean isRed;

    public RedBlackNode(E data) {
            super(data);
            isRed = true;
    }

    @Override
    public String toString() {
            if(isRed){
                return "Red: " + super.toString();
            } else {
                return "Black: " + super.toString();
            }
    }

  }

  public boolean add(E item) {
        if(root == null){
            root = new RedBlackNode<E>(item);
            ((RedBlackNode<E>) root).isRed = false;
            return true;

        } else {
            root = add((RedBlackNode<E>) root, item);
            ((RedBlackNode<E>) root).isRed = false;
            return addReturn;
        }
  }

  private BinaryTree.Node<E> add(RedBlackTree.RedBlackNode<E> localRoot, E item) {
        int compare = item.compareTo(localRoot.data);
        if(compare == 0){
            //Item is already in tree
            addReturn = false;
            return localRoot;
        } else if (compare < 0){
            //item < localRoot.data
            if(localRoot.left == null){
                //Create new left child
                localRoot.left = new RedBlackNode<E>(item);
                addReturn = true;
                return localRoot;
            } else { //Need to search
                //Check for two red children, swap colors if found
                moveBlackDown(localRoot);
                //Recursively add on the left
                localRoot.left = add((RedBlackNode<E>) localRoot.left, item);
                //See whether the left child is now red
                if(((RedBlackNode<E>) localRoot.left).isRed){
                    if(localRoot.left.left != null
                            && ((RedBlackNode<E>) localRoot.left.left).isRed){
                        // Left-left grand-child is also red
                        // Single rotation is necessary; change colors
                        ((RedBlackNode<E>) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    } else if (localRoot.left.right != null
                            && ((RedBlackNode<E>) localRoot.left.right).isRed){
                        // Left-right grand-child is also red
                        // Double rotation is necessary; change colors
                        localRoot.left = rotateLeft(localRoot.left);
                        ((RedBlackNode<E>) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    }
                }
                return localRoot;
            }
        } else {
            //item is greater than localRoot.data
            if(localRoot.right == null){
                //Create new right child
                localRoot.right = new RedBlackNode<E>(item);
                addReturn = true;
                return localRoot;
            } else { //Need to search
                //Check for two red children, swap if needed
                moveBlackDown(localRoot);
                //Recursively add on the right
                localRoot.right = add((RedBlackNode<E>) localRoot.right, item);
                //See whether right child is now red
                if(((RedBlackNode<E>) localRoot.right).isRed){
                    if(localRoot.right.right != null
                            && ((RedBlackNode<E>) localRoot.right.right).isRed){
                        //Right-right grand-child is also red
                        //Single rotation is necessary, change colors
                        ((RedBlackNode<E>) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateLeft(localRoot);
                    } else if (localRoot.right.left != null
                            && ((RedBlackNode<E>) localRoot.right.left).isRed){
                        //Right-left grand-child is also red
                        //Double rotation is necessary; change colors
                        localRoot.right = rotateRight(localRoot.right);
                        ((RedBlackNode<E>) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateLeft(localRoot);
                    }
                }
                return localRoot;
            }
        }
  }

  private void moveBlackDown(RedBlackTree.RedBlackNode<E> localRoot) {
        if(localRoot.left != null && localRoot.right != null){ //If a child is null, it is black
            if(((RedBlackNode<E>) localRoot.left).isRed
                    && ((RedBlackNode<E>) localRoot.right).isRed){ //if both children are red, swap colors
                ((RedBlackNode<E>) localRoot.left).isRed = false;
                ((RedBlackNode<E>) localRoot.right).isRed = false;
                localRoot.isRed = true;
            }
        }
  }

}
