package Cache.eviction;

import java.util.HashMap;
import java.util.Map;

// LFU Strategy: Track access frequency
public class LFUEvictionPolicy<K> implements EvictionPolicy<K> {
	private final Map<K, Integer> freqMap = new HashMap<>();

	@Override
	public void keyAccessed(K key) {
		freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
	}

	@Override
	public K evictKey() {
		return freqMap.entrySet().stream()
				.min(Map.Entry.comparingByValue())
				.map(entry -> {
					freqMap.remove(entry.getKey());
					return entry.getKey();
				})
				.orElse(null);
	}
}
