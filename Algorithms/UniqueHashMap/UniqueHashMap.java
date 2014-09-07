import java.util.*;

public class UniqueHashMap<K, V> extends HashMap<K, V> {
    private HashMap<V, K> reverseMap = null;

    /**
     * Associates the specified value with the specified key in this map.
     * Each value is mapped to by at most one key.
     * If the value already exists in the map, the old key should be replaced.
     * 
     * Complexity is O(1) assuming get, put, remove and containsKey takes constant time.
     *
     * @param  key   key with which the specified value is to be associated
     * @param  value value to be associated with the specified key
     * @return       the previous value associated with key, or null if there was no mapping for key.
     */
    public V put(K key, V value) {
        if (reverseMap == null) {
            reverseMap = new HashMap<V, K>();
        }

        // make sure value is unique by checking the reverse map
        if (reverseMap.containsKey(value)) {
            super.remove(reverseMap.get(value));
        }

        // get old value associated with key, or null if doesn't exist
        V output = null;
        if (this.containsKey(key)) {
            output = this.get(key);
            reverseMap.remove(output);
        }

        // store key value pair in both maps
        super.put(key, value);
        reverseMap.put(value, key);

        return output;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * Complexity is O(1) assuming get, remove and containsKey takes constant time.
     *
     * @param  key  key whose mapping is to be removed from the map
     * @return      the previous value associated with key, or null if there was no mapping for key. 
     */
    public V remove(Object key) {
        if (reverseMap == null) {
            reverseMap = new HashMap<V, K>();
        }

        V output = null;
        if (this.containsKey(key)) {
            output = this.get(key);
            reverseMap.remove(output);
        }
        return output;
    }
}