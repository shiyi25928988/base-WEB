package lego.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;

import lego.ioc.RestApiService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatcherServlet extends HttpServlet {

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

	@Inject
	RestApiService restService;
	
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
		switch (req.getMethod()) {
		case METHOD_DELETE:
			restService.doDelete();
			break;
		case METHOD_HEAD:
			restService.doHead();
			break;
		case METHOD_GET:
			restService.doGet();
			break;
		case METHOD_OPTIONS:
			restService.doOptions();
			break;
		case METHOD_POST:
			restService.doPost();
			break;
		case METHOD_PUT:
			restService.doPut();
			break;
		case METHOD_TRACE:
			restService.doTrace();
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "HTTP METHOD NOT SUPPORT");

		}

	}

}
