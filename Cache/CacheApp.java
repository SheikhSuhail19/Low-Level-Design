package Cache;

import Cache.eviction.EvictionPolicy;
import Cache.eviction.FIFOEvictionPolicy;
import Cache.eviction.HybridEvictionPolicy;
import Cache.eviction.LFUEvictionPolicy;
import Cache.eviction.LRUEvictionPolicy;
import Cache.storage.CacheStore;
import Cache.storage.HashMapCacheStore;

public class CacheApp {
	public static void main(String[] args) throws InterruptedException {
		testFIFO();
		testLRU();
		testLFU();
		testHybrid();
		testTTL();
	}

	private static void testFIFO() {
		System.out.println("=== FIFO Eviction ===");
		EvictionPolicy<String> policy = new FIFOEvictionPolicy<>();
		executeTest(policy, "FIFO");
	}

	private static void testLRU() {
		System.out.println("\n=== LRU Eviction ===");
		EvictionPolicy<String> policy = new LRUEvictionPolicy<>();
		executeTest(policy, "LRU");
	}

	private static void testLFU() {
		System.out.println("\n=== LFU Eviction ===");
		EvictionPolicy<String> policy = new LFUEvictionPolicy<>();
		CacheStore<String, String> store = new HashMapCacheStore<>();
		Cache<String, String> cache = new Cache<>(3, policy, store);

		cache.put("A", "Apple");
		cache.put("B", "Banana");
		cache.put("C", "Cat");
		cache.get("A");
		cache.get("A");
		cache.get("B");
		cache.put("D", "Dog"); // This should evict "C" in LFU

		printResult("LFU", cache, "A", "B", "C", "D");
	}

	private static void testHybrid() {
		System.out.println("\n=== Hybrid Eviction (LRU + LFU) ===");
		EvictionPolicy<String> policy = new HybridEvictionPolicy<>(1, 1);
		executeTest(policy, "Hybrid");
	}

	private static void testTTL() throws InterruptedException {
		System.out.println("\n=== TTL Eviction ===");
		EvictionPolicy<String> policy = new FIFOEvictionPolicy<>(); // Placeholder, implement TTL policy
		CacheStore<String, String> store = new HashMapCacheStore<>();
		Cache<String, String> cache = new Cache<>(3, policy, store);

		cache.put("A", "Apple");
		cache.put("B", "Banana");
		cache.put("C", "Cat");

		Thread.sleep(2000); // Simulate time passing
		cache.put("D", "Dog"); // This should evict "A" if TTL is implemented

		Thread.sleep(1000); // Simulate more time passing
		cache.put("E", "Elephant"); // This should evict "B" if TTL is implemented

		printResult("TTL", cache, "A", "B", "C", "D", "E");
	}

	private static void executeTest(EvictionPolicy<String> policy, String label) {
		CacheStore<String, String> store = new HashMapCacheStore<>();
		Cache<String, String> cache = new Cache<>(3, policy, store);

		cache.put("A", "Apple");
		cache.put("B", "Banana");
		cache.put("C", "Cat");
		cache.get("A");
		cache.put("D", "Dog"); // This should evict "A" in FIFO, "B" in LRU, "B" in Hybrid

		printResult(label, cache, "A", "B", "C", "D");
	}

	private static void printResult(String label, Cache<String, String> cache, String... keys) {
		for (String key : keys) {
			System.out.printf("[%s] %s: %s\n", label, key, cache.get(key));
		}
	}
}