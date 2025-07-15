package Cache.eviction;

// Interface for all cache eviction strategies
public interface EvictionPolicy<K> {
	void keyAccessed(K key);

	K evictKey();
}
