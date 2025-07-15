package Cache.eviction;

import java.util.LinkedList;
import java.util.Queue;

// FIFO Strategy: Maintain insertion order
public class FIFOEvictionPolicy<K> implements EvictionPolicy<K> {
	private final Queue<K> queue = new LinkedList<>();

	@Override
	public void keyAccessed(K key) {
		if (!queue.contains(key)) queue.offer(key);
	}

	@Override
	public K evictKey() {
		return queue.poll();
	}
}
