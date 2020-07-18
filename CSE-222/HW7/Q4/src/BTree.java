
public class BTree<E extends Comparable<E>> implements SearchTree<E>
{
    private Node<E> root = null;

    private int order;

    private E newParent;

    private Node<E> newChild;

    private static class Node<E> {
        private int size = 0;

        private E[] data;

        private Node<E>[] child;

        @SuppressWarnings("unchecked")
        public Node(int order) {
            data = (E[]) new Comparable[order - 1];
            child = (Node<E>[]) new Node[order];
            size = 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size - 1; ++i) {
                sb.append(data[i].toString());
                sb.append(", ");
            }
            sb.append(data[size - 1].toString());
            return sb.toString();
        }
    }

    public BTree(int order) {
        if (order < 3) throw new IllegalArgumentException("order < 3");
        this.order = order;
        this.root = null;
    }

    @Override
    public boolean contains(E target) { return find(target) != null; }

    @Override
    public E find(E item) { return find(root, item); }

    private E find(Node<E> node, E item) {

        if (node == null) return null;

        int index = 0;

        while (index < node.size && item.compareTo(node.data[index]) > 0) ++index;

        if (index == node.size || item.compareTo(node.data[index]) < 0) return find(node.child[index], item);
        else return node.data[index];
    }

    @Override
    public boolean add(E item) {

        if (order == 0) throw new IllegalStateException("Must set order first");

        if (root == null) {
            root = new Node<E>(order);
            root.data[0] = item;
            root.size = 1;
            return true;
        }

        newChild = null;
        boolean result = insert(root, item);
        if (newChild != null) {
            Node<E> newRoot = new Node<E>(order);
            newRoot.child[0] = root;
            newRoot.child[1] = newChild;
            newRoot.data[0] = newParent;
            newRoot.size = 1;
            root = newRoot;
        }
        return result;
    }

    private boolean insert(Node<E> root, E item) {

        int index = binarySearch(item, root.data, 0, root.size);

        if (index != root.size)
            if (item.compareTo(root.data[index]) == 0) return false;

        if (root.child[index] == null) {

            if (root.size < order - 1) {
                insertIntoNode(root, index, item, null);
                newChild = null;
            }
            else { splitNode(root, index, item, null); }

            return true;

        } else {

            boolean result = insert(root.child[index], item);

            if (newChild != null) {

                if (root.size < order - 1) {
                    insertIntoNode(root, index, newParent, newChild);
                    newChild = null;
                }
                else { splitNode(root, index, newParent, newChild); }
            }
            return result;
        }
    }

    public int binarySearch(E target, E[] items, int first, int last) {

        if (target.compareTo(items[last - 1]) > 0) return last;

        int start = first;
        int end = last;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (target.compareTo(items[middle]) < 0) end = middle - 1;

            if (target.compareTo(items[middle]) > 0) start = middle + 1;

            if (target.compareTo(items[middle]) == 0) return middle;
        }

        for (int i = first; i < last; i++)
            if (target.compareTo(items[i]) < 0) return i;

        return last;
    }

    private void insertIntoNode(Node<E> node, int index, E item, Node<E> child) {

        for (int i = node.size; i > index; i--) {
            node.data[i] = node.data[i - 1];
            node.child[i + 1] = node.child[i];
        }

        node.data[index] = item;
        node.child[index + 1] = child;
        node.size++;
    }


    private void splitNode(Node<E> node, int index, E item, Node<E> child) {

        newChild = new Node<E>(order);

        int numToMove = (order - 1) - ((order - 1) / 2);

        if (index > (order - 1) / 2) numToMove--;


        System.arraycopy(node.data, order - numToMove - 1, newChild.data, 0, numToMove);
        System.arraycopy(node.child, order - numToMove, newChild.child, 1, numToMove);

        node.size = order - numToMove - 1;
        newChild.size = numToMove;

        if (index == ((order - 1) / 2)) {
            newParent = item;
            newChild.child[0] = child;

        } else {
            if (index < ((order - 1) / 2)) insertIntoNode(node, index, item, child);

            else insertIntoNode(newChild, index - ((order - 1) / 2) - 1, item, child);

            newParent = node.data[node.size - 1];

            newChild.child[0] = node.child[node.size];
            node.size--;
        }

        for (int i = node.size; i < node.data.length; i++) {
            node.data[i] = null;
            node.child[i + 1] = null;
        }
    }

    @Override
    public boolean remove(E o) { throw new UnsupportedOperationException("Remove from B-trees not implemented"); }

    @Override
    public E delete(E o) { throw new UnsupportedOperationException("Delete from B-trees not implemented"); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 0, sb);

        return sb.toString();
    }

    private void preOrderTraverse(Node<E> node, int d, StringBuilder sb) {

        for (int i = 0; i != d; ++i) sb.append("  ");

        if (node == null) sb.append("null");

        else {
            for (int i = 0; i != node.size; ++i) {
                sb.append(node.data[i]);
                if (i != node.size - 1) sb.append(", ");
            }
            sb.append("\n");

            for (int i = 0; i != node.size; ++i) {
                preOrderTraverse(node.child[i], d + 1, sb);
                sb.append("\n");
            }

            preOrderTraverse(node.child[node.size], d + 1, sb);
            sb.append("\n\n");
        }
    }
}