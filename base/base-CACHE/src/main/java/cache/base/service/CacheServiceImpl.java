package cache.base.service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author yshi
 *
 */
public class CacheServiceImpl implements CacheService<String, Object> {

	private Cache<String, Object> cache;


	private final static CacheService<String, Object> cacheInstance;

	static {
		cacheInstance = new CacheServiceImpl();
	}

	/**
	 * @return
	 */
	public static CacheService<String, Object> getCacheInstance() {
		return cacheInstance;
	}

	/**
	 * 
	 */
	private CacheServiceImpl() {
		cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(10_000).build();
	}

	/* (non-Javadoc)
	 * @see cache.base.service.CacheService#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(String key, Object value) {
		cache.put(key, value);
	}

	/* (non-Javadoc)
	 * @see cache.base.service.CacheService#get(java.lang.Object)
	 */
	@Override
	public Optional<Object> get(String key) {
		Object value = cache.getIfPresent(key);
		if (Objects.nonNull(value)) {
			return Optional.of(value);
		} else {
			return Optional.empty();
		}
	}

	/* (non-Javadoc)
	 * @see cache.base.service.CacheService#evcit(java.lang.Object)
	 */
	@Override
	public void evcit(String key) {
		cache.invalidate(key);
	}

}
