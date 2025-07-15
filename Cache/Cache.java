package Cache;

// LLD of a Cache System Supporting Multiple Caching Policies
// Uses SOLID Principles and Design Patterns

import Cache.eviction.EvictionPolicy;
import Cache.storage.CacheStore;

// Generic Cache class
class Cache<K, V> {
	private final int capacity;
	private final EvictionPolicy<K> evictionPolicy;
	private final CacheStore<K, V> store;

	public Cache(int capacity, EvictionPolicy<K> evictionPolicy, CacheStore<K, V> store) {
		this.capacity = capacity;
		this.evictionPolicy = evictionPolicy;
		this.store = store;
	}

	public void put(K key, V value) {
		if (store.size() >= capacity) {
			K evict = evictionPolicy.evictKey();
			if (evict != null) {
				store.remove(evict);
			}
		}
		store.put(key, value);
		evictionPolicy.keyAccessed(key);
	}

	public V get(K key) {
		if (!store.contains(key)) return null;
		evictionPolicy.keyAccessed(key);
		return store.get(key);
	}
}

