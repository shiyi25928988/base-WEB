package http.proxy.vertx;

import java.io.IOException;

import http.proxy.core.config.PathMapRegister;
import http.proxy.core.http.utils.OkHttpClientPool;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VertxMain {

	public static void main(String...strings) {
		Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
		
		
		
		HttpServer server = vertx.createHttpServer();
		
		server.requestHandler(request -> {
			
			System.out.println(request.uri());
			
			String url = PathMapRegister.getMapedRemoteURL(request.uri());
			
			OkHttpClient okHttpClient = OkHttpClientPool.borrowOkHttpClient();
			Request rrequest = new Request.Builder().url(request.uri()).build();
			try {
				Response response = okHttpClient.newCall(rrequest).execute();
				request.response().end(new String(response.body().bytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			
		  // Handle the request in here
			//System.out.print(request.method());
			
			
		});server.listen(8888);
	}
}
