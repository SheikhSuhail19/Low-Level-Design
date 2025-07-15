package Cache.eviction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TTLEvictionPolicy<K> implements EvictionPolicy<K> {
	private final long ttlMillis;
	private final Map<K, Long> accessTimes = new HashMap<>();

	public TTLEvictionPolicy(long ttlMillis) {
		this.ttlMillis = ttlMillis;
	}

	@Override
	public void keyAccessed(K key) {
		accessTimes.put(key, System.currentTimeMillis());
	}

	@Override
	public K evictKey() {
		long now = System.currentTimeMillis();
		for (Iterator<Entry<K, Long>> it = accessTimes.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<K, Long> entry = it.next();
			if (now - entry.getValue() > ttlMillis) {
				it.remove();
				return entry.getKey();
			}
		}
		return null; // No keys to evict
	}
}
