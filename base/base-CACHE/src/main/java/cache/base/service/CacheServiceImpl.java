package cache.base.service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CacheServiceImpl implements CacheService<String, Object> {

	private Cache<String, Object> cache;

	private static boolean initFlag = false;

	private final static CacheService<String, Object> cacheInstance;

	static {
		cacheInstance = new CacheServiceImpl();
	}

	public static CacheService<String, Object> getCacheInstance() {
		return cacheInstance;
	}

	private CacheServiceImpl() {
		if (initFlag)
			throw new RuntimeException("CacheService instance already constructed");
		else {
			cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(10_000).build();
			initFlag = true;
		}
	}

	@Override
	public void put(String key, Object value) {
		cache.put(key, value);

	}

	@Override
	public Optional<Object> get(String key) {
		Object value = cache.getIfPresent(key);
		if (Objects.nonNull(value)) {
			return Optional.of(value);
		} else {
			return Optional.empty();
		}

	}

	@Override
	public void evcit(String key) {
		cache.invalidate(key);
	}

}
