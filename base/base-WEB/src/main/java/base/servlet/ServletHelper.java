package base.servlet;

import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ServletHelper {

	private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<>();
	
	private ServletContext context;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	private ServletHelper(ServletContext context, HttpServletRequest req, HttpServletResponse resp) {
		this.context = context;
		this.req = req;
		this.resp = resp;
	}
	
	private ServletContext getServletContext() {
		return this.context;
	}
	
	private HttpServletRequest getHttpServletRequest() {
		return this.req;
	}
	
	private HttpServletResponse getHttpServletResponse() {
		return this.resp;
	}
	
	public static void init(ServletContext context, HttpServletRequest req, HttpServletResponse resp) {
		SERVLET_HELPER_HOLDER.set(new ServletHelper(context, req, resp));
	}
	
	public static void init(HttpServletRequest req, HttpServletResponse resp) {
		SERVLET_HELPER_HOLDER.set(new ServletHelper(null, req, resp));
	}
	
	public static void destory() {
		SERVLET_HELPER_HOLDER.remove();
	}
	
	public static ServletContext getContext() {
		return SERVLET_HELPER_HOLDER.get().getServletContext();
	}
	
	public static HttpServletRequest getRequest() {
		return SERVLET_HELPER_HOLDER.get().getHttpServletRequest();
	}
	
	public static HttpServletResponse getResponse() {
		return SERVLET_HELPER_HOLDER.get().getHttpServletResponse();
	}
	
	public static String getRealPath() {
		if(Objects.nonNull(getContext())) {
			return getContext().getRealPath("/");
		}else {
			return "";
		}
		
	}
}
