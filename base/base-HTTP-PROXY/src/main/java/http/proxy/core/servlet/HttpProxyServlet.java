package http.proxy.core.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import http.proxy.core.http.utils.MimeType;
import http.proxy.core.http.utils.OkHttpClientPool;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class HttpProxyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String METHOD_DELETE = "DELETE";
	private static final String METHOD_HEAD = "HEAD";
	private static final String METHOD_GET = "GET";
	private static final String METHOD_OPTIONS = "OPTIONS";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_PUT = "PUT";
	private static final String METHOD_TRACE = "TRACE";

	private static final String HEADER_IFMODSINCE = "If-Modified-Since";
	private static final String HEADER_LASTMOD = "Last-Modified";

	private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
	private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
	
	private ServletContext servletContext;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("DispatcherServlet init...");
		servletContext = config.getServletContext();
	}

	@Override
	public void destroy() {
		log.info("DispatcherServlet destroy...");
		ServletHelper.destory();
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("service...");
		ServletHelper.init(servletContext, req, resp);
		
		log.info(req.getRequestURI());
		
		OkHttpClient okHttpClient = OkHttpClientPool.borrowOkHttpClient();
		Request request = new Request.Builder().url("https://www.baidu.com").build();
		Response response = okHttpClient.newCall(request).execute();
		
		log.info("receive resp in : " + response.receivedResponseAtMillis() + "ms");
		
		
		response.headers().forEach(header -> {
			log.info("header : " + header.getFirst());
			log.info("header : " + header.getSecond());
			resp.addHeader(header.getFirst(), header.getSecond());
		});
		
		log.info("protocol : " + response.protocol().name());
		log.info("contentType : " + response.body().contentType().toString());
		
		resp.setContentType(response.body().contentType().toString());
		resp.setCharacterEncoding("UTF-8");
		
		Writer writer = resp.getWriter();
		response.body().charStream().transferTo(writer);
		writer.flush();
		writer.close();
		
		OkHttpClientPool.returnOkHttpClient(okHttpClient);
		
	}
}
