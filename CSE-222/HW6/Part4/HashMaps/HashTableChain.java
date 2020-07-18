import java.util.LinkedList;

public class HashTableChain<K, V extends Comparable<V>> implements KWHashMap<K, V>
{
    private BinarySearchTree<Entry<K, V>>[] table;

    private int numKeys;

    private static final int CAPACITY = 101;

    private static final double LOAD_THRESHOLD = 3.0;

    /**
     * Contains key-value pairs for a hash table
     */
    private static class Entry<K, V extends Comparable<V>> implements Comparable<Entry<K, V>> {
        private K key;
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key
         * @return The key
         */
        public K getKey() { return key; }
        /**
         * Retrieves the value
         * @return The value
         */
        public V getValue() { return value; }

        /**
         * Sets the value
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;

            return oldVal;
        }

        @Override
        public String toString() {
            return "Key: " + key + " - " +
                    "Value: "  + value + "\n";
        }

        @Override
        public int compareTo(Entry<K, V> kvEntry) {
           return this.value.compareTo(kvEntry.value);
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Entry) {
                Entry<K, V> temp = (Entry<K, V>) obj;

                return temp.key == this.key;
            }

            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTableChain() { table = new BinarySearchTree[CAPACITY]; }

    /**
     * Method get for class HashTableChain.
     * @param key The key being sought
     * @return The value associated with this key if found; otherwise, null
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;

        if(index < 0)
            index += table.length;
        if(table[index] == null)
            return null;

        for (BinarySearchTree<Entry<K, V>> entryBinarySearchTree : table) {
            if(entryBinarySearchTree.equals(key))
                return null;
        }

        return null;
    }

    /**
     * Method put for class HashTableChain.
     * post: This key-value pair is inserted in the table and numKeys is incremented.
     * If the key is already in the table, its value is changed to the argument value
     * and numKeys is not changed.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return The old value associated with this key if found; otherwise, null
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;

        if(index < 0)
            index += table.length;

        if (table[index] ==  null)
            table[index] = new BinarySearchTree<Entry<K, V>>();

        for (BinarySearchTree<Entry<K, V>> entryBinarySearchTree : table) {
            if (entryBinarySearchTree != null) {
                if (entryBinarySearchTree.find(new Entry<>(key, value)) != null) {
                    entryBinarySearchTree.add(new Entry<>(key, value));
                }
            }
        }

        table[index].add(new Entry<>(key, value));
        ++numKeys;

        return null;
    }

    /**
     * empty or not method
     * @return true if map is empty, otherwise false
     */
    @Override
    public boolean isEmpty() { return table.length == 0; }

    /**
     * Remove method
     * @param key removing value of key
     * @return removing value
     */
    @SuppressWarnings("unchecked")
    @Override
    public V remove(Object key)
    {
        int index = key.hashCode() % table.length;

        if(index < 0)
            index += table.length;

        V oldVal = table[index].getData().value;

        table[index].remove(new Entry<K, V>((K) key, this.get(key)));

        return null;
    }

    /**
     * size method
     * @return size of hash table
     */
    @Override
    public int size() { return table.length; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (BinarySearchTree<Entry<K, V>> entryBinarySearchTree : table) {
            if (entryBinarySearchTree != null) {
                sb.append(entryBinarySearchTree.toString());
            }
        }

        return sb.toString();
    }
}
