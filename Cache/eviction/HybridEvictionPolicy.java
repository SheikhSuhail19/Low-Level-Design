package Cache.eviction;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// Hybrid strategy: LRU + LFU combination
public class HybridEvictionPolicy<K> implements EvictionPolicy<K> {
	private final Map<K, Integer> freqMap = new HashMap<>();
	private final LinkedHashMap<K, Long> accessMap = new LinkedHashMap<>();
	private final long lruWeight;
	private final long lfuWeight;

	public HybridEvictionPolicy(long lruWeight, long lfuWeight) {
		this.lruWeight = lruWeight;
		this.lfuWeight = lfuWeight;
	}

	@Override
	public void keyAccessed(K key) {
		freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
		accessMap.remove(key);
		accessMap.put(key, System.nanoTime());
	}

	@Override
	public K evictKey() {
		return accessMap.keySet().stream()
				.min(Comparator.comparingLong(this::score))
				.map(k -> {
					accessMap.remove(k);
					freqMap.remove(k);
					return k;
				}).orElse(null);
	}

	private long score(K key) {
		long accessTime = accessMap.getOrDefault(key, 0L);
		int freq = freqMap.getOrDefault(key, 0);
		return lruWeight * accessTime - lfuWeight * freq;
	}
}
