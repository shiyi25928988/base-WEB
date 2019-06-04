package cache.base.service;

import java.util.Optional;

public interface CacheService<K, V> {
	
	void put(K key, V value);
	
	Optional<V> get(K key);
	
	void evcit(K key);
}
