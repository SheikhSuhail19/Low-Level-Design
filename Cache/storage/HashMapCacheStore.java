package Cache.storage;

import java.util.HashMap;
import java.util.Map;

// In-memory hashmap store
public class HashMapCacheStore<K, V> implements CacheStore<K, V> {
	private final Map<K, V> map = new HashMap<>();

	@Override
	public void put(K key, V value) {
		map.put(key, value);
	}

	@Override
	public V get(K key) {
		return map.get(key);
	}

	@Override
	public boolean contains(K key) {
		return map.containsKey(key);
	}

	@Override
	public void remove(K key) {
		map.remove(key);
	}

	@Override
	public int size() {
		return map.size();
	}
}
