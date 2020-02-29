package base.jetty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author shiyi
 *
 */
public final class ServerFactory {

	/**
	 * @param port
	 * @return
	 */
	public static Server buildServer(int port) {
		return buildServer(port, null);
	}

	/**
	 * @param port
	 * @param handler
	 * @return
	 */
	public static Server buildServer(int port, ServletHandler handler) {
		Server server = new Server(port);
		server.setHandler(handler);
		return buildServer(port, handler, null);
	}

	/**
	 * @param port
	 * @param handler
	 * @param servlets
	 * @return
	 */
	public static Server buildServer(int port, ServletHandler handler, Servlet... servlets) {
		Server server = new Server(port);
		if (!Objects.isNull(handler)) {
			if ((!Objects.isNull(servlets)) && servlets.length > 0) {
				List<ServletHolder> holderList = new ArrayList<>();
				Stream.of(servlets).forEach(s -> {
					holderList.add(new ServletHolder(s));
				});
				handler.setServlets((ServletHolder[]) holderList.toArray());
			}
			server.setHandler(handler);
		}
		return server;
	}
}
