package org.yshi.server;

import org.yshi.server.handler.DefaultActionHandler;
import org.yshi.shared.action.DefaultAction;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class ActionHandlerModule extends net.customware.gwt.dispatch.server.guice.ActionHandlerModule{

	@Override
	protected void configureHandlers() {
		/*
		 * Bind action handler class to the action.
		 * aka : bindHandler(AbstractAction.class, AbstractActionHandler.class);
		 * */
		log.info("ActionHandlerModule configureHandlers...");
		
		bindHandler(DefaultAction.class, DefaultActionHandler.class);
		
	}

}
