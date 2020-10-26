package http.proxy.core.http.utils;

import java.util.Stack;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
public class OkHttpClientPool {

	private static Stack<OkHttpClient> pool = new Stack<>();
	
	private static int poolSize = 16;
	
	static {
		initPool();
	}
	
	private static void initPool() {
		pool.push(new OkHttpClient());
	}
	
	public static OkHttpClient borrowOkHttpClient() {
		log.info("pool size : " + pool.size());
		if(pool.isEmpty()) {
			return new OkHttpClient();
		}
		return pool.pop();
	}
	
	public static void returnOkHttpClient(OkHttpClient okHttpClient) {
		if(pool.size() > poolSize) {
			okHttpClient = null;
		} else {
			pool.push(okHttpClient);
		}
		log.info("pool size : " + pool.size());
	}
	
}
