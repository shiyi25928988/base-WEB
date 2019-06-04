package cache.base.aop;

import java.util.Optional;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

import cache.base.service.CacheService;

@SuppressWarnings("rawtypes")
public class CacheInterceptor implements MethodInterceptor {
	
	@Inject
	CacheService cacheService;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		Cache cache = invocation.getMethod().getAnnotation(Cache.class);
		String key = cache.key();
		Optional<?> op = cacheService.get(key);
		if(op.isPresent()) {
			return op.get();
		}else {
			return invocation.proceed();
		}
	}

}
