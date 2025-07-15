package Cache.storage;

// Cache Storage abstraction
public interface CacheStore<K, V> {
	void put(K key, V value);

	V get(K key);

	boolean contains(K key);

	void remove(K key);

	int size();
}
