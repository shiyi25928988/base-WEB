package base.jetty.handlers;

import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public final class HandlerFactory {

	/**
	 * A Handler Collection that calls each handler in turn until either an
	 * exception is thrown, the response is committed or the request.isHandled()
	 * returns true. You can use it to combine handlers that conditionally handle a
	 * request, such as calling multiple contexts until one matches a virtual host.
	 * 
	 * @return HandlerList
	 */
	public static HandlerList getHandlerList() {
		HandlerList list = new HandlerList();
		
		//ServletHandler map request to servlet object
		list.addHandler(new ServletHandler());
		
		list.addHandler(new ResourceHandler());
		
		list.addHandler(new WebAppContext());
		
		list.addHandler(new DefaultHandler());


		return list;

	}

	/**
	 * Holds a collection of other handlers and calls each handler in order. This is
	 * useful for combining statistics and logging handlers with the handler that
	 * generates the response.
	 * 
	 * @return HandlerCollection
	 */
	public static HandlerCollection getHandlerCollection() {
		HandlerCollection collection = new HandlerCollection();
		return collection;
	}

	/**
	 * A Handler base class that you can use to daisy chain handlers together in the
	 * style of aspect-oriented programming. For example, a standard web application
	 * is implemented by a chain of a context, session, security and servlet
	 * handlers.
	 * 
	 * @return HandlerWrapper
	 */
	public static HandlerWrapper getHandlerWrapper() {
		HandlerWrapper wrapper = new HandlerWrapper();
		return wrapper;
	}

	/**
	 * A specialized HandlerCollection that uses the longest prefix of the request
	 * URI (the contextPath) to select a contained ContextHandler to handle the
	 * request.
	 * 
	 * @return
	 */
	public static ContextHandlerCollection getContextHandlerCollection() {
		ContextHandlerCollection collection = new ContextHandlerCollection();
		return collection;
	}
}
