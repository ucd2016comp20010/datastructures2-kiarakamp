package project20280.hashtable;

import project20280.interfaces.Entry;

import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = new MapEntry[capacity];
    }

    // returns true if the slot is empty or DEFUNCT
    private boolean isAvailable(int i) {
        return (table[i] == null || table[i] == DEFUNCT);
    }

    int findSlot(int h, K k) {
        // TODO
        int avail = -1;
        int i = h;
        do {
            if (isAvailable(i)) {
                if (avail == -1) {
                    avail = i;
                }
                if (table[i] == null) {
                    break;
                }
            } else if (table[i].getKey().equals(k)) {
                return i;
            }
            i = (i + 1) % capacity;
        } while (i != h);
        return -(avail + 1);
    }

    @Override
    protected V bucketGet(int h, K k) {
        // TODO
        int i  = findSlot(h, k);
        if (i < 0) {
            return null;
        }
        return table[i].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        // TODO
        int i = findSlot(h, k);
        if (i >= 0) {
            return table[i].setValue(v);
        }
        table[-(i+1)] = new MapEntry<>(k,v);
        n++;
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        // TODO
        int i = findSlot(h, k);
        if (i < 0) {
            return null;
        }
        V answer = table[i].getValue();
        table[i] = DEFUNCT;
        n--;
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (!isAvailable(h)) {
                buffer.add(table[h]);
            }
        }
        return buffer;
    }
}
