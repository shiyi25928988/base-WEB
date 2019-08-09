package org.yshi.client;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;

/**
 * @author yshi
 *
 */
public abstract class AsyncDispatcher {

	/**
	 * 
	 */
	public static final DispatchAsync dispatcher = new StandardDispatchAsync(new DefaultExceptionHandler());
}
