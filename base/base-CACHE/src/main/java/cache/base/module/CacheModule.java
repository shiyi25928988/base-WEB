package cache.base.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

import cache.base.aop.Cache;
import cache.base.aop.CacheInterceptor;
import cache.base.service.CacheService;
import cache.base.service.CacheServiceImpl;

public class CacheModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CacheService.class).toProvider(CacheServiceProvider.class).in(Singleton.class);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cache.class), new CacheInterceptor());
	}

	@SuppressWarnings("rawtypes")
	public static class CacheServiceProvider implements Provider<CacheService> {

		@Override
		public CacheService get() {
			return CacheServiceImpl.getCacheInstance();
		}
		
	}

}
