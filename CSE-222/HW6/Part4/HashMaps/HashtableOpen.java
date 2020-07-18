public class HashtableOpen<K, V> implements KWHashMap<K, V>
{
    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 101;
    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private final Entry<K, V> DELETED = new Entry<K, V>(null, null);

    @SuppressWarnings("unchecked")
    public HashtableOpen() {
        table = new Entry[START_CAPACITY];
    }

    /**
     * Contains key-value pairs for a hash table
     */
    private static class Entry<K, V>
    {
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
    }

    /**
     * Method put for class HashTableOpen.
     * post: This key-value pair is inserted in the table and numKeys is incremented.
     * If the key is already in the table, its value is change to the argument value
     * and numKeys is not changed. If the LOAD_THRESHOLD is exceeded, the table is expanded.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return Old value associated with this key if found; otherwise, null
     */
    @Override
    public V put(K key, V value) {
        int index = find(key);

        if(table[index] == null)  {
            table[index] = new Entry<>(key, value);
            ++numKeys;

            double loadfactor = (double)  (numKeys + numDeletes) / table.length;

            if(loadfactor >  LOAD_THRESHOLD)
                rehash();

            return null;
        }

        V oldVal = table[index].value;
        table[index].value = value;

        return oldVal;
    }

    /**
     * Finds either the target key or first empty slot in the search chain using linear probing.
     * pre: The table is not full.
     * @param key The key of the target object
     * @return The position of the target of the first empty slot if the target is not in the table
     */
    private int find(Object key) {
        int index = key.hashCode() % table.length;
        int i = 0;

        while(index < 0)
            index +=  table.length;

        while((table[index] != null) && (!key.equals(table[index].key))) {
            index = (key.hashCode() + ((++i) * secondHash(key))) % table.length;

            while(index < 0)
                index +=  table.length;

            if(index >= table.length)
                index = 0;
        }

        return index;
    }

    private int secondHash(Object key) { return key.hashCode()*13; }

    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD
     * post: The size of the table is doubled ans is an odd integer.
     * Each non deleted entry from the original table is
     * reinserted into the expanded table.
     * The value of numKeys is reset to the number of items
     * actually inserted; numDeletes is reset to 0.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldTable = table;

        table = new Entry[2 * oldTable.length + 1];

        numKeys = 0;
        numDeletes = 0;

        for (Entry<K, V> kvEntry : oldTable) {
            if ((kvEntry != null) && (kvEntry != DELETED)) {
                put(kvEntry.key, kvEntry.value);
            }
        }
    }

    /**
     * Method for class HashTableOpen
     * @param key the key being sought
     * @return the value associated with this key if found; otherwise, null
     */
    @Override
    public V get(Object key) {
        int index = find(key);

        if(table[index] != null)
            return table[index].value;
        else
            return null;
    }

    /**
     * is empty method
     * @return true if table is empty, otherwise false
     */
    @Override
    public boolean isEmpty() { return (table.length == 0); }

    /**
     * remove method
     * @param key of value removing
     * @return value of key
     */
    @Override
    public V remove(Object key) {
        int index = find(key);

        V oldVal = table[index].value;
        table[index] = null;

        return oldVal;
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

        for(Entry<K, V> e : table) {
            if(e != null)
                sb.append(e.toString());
        }

        return sb.toString();
    }
}
