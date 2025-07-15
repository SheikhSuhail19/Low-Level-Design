package Cache.eviction;

import java.util.Iterator;
import java.util.LinkedHashSet;

// LRU Strategy: Move to front on access
public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
	private final LinkedHashSet<K> set = new LinkedHashSet<>();

	@Override
	public void keyAccessed(K key) {
		set.remove(key);
		set.add(key);
	}

	@Override
	public K evictKey() {
		Iterator<K> it = set.iterator();
		if (it.hasNext()) {
			K oldest = it.next();
			set.remove(oldest);
			return oldest;
		}
		return null;
	}
}
