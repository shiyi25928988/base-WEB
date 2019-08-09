package org.yshi.server.handler;

import org.yshi.shared.action.DefaultAction;
import org.yshi.shared.result.DefaultResult;

import lombok.extern.slf4j.Slf4j;

import net.customware.gwt.dispatch.server.AbstractActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

/**
 * @author yshi
 *
 */
@Slf4j
public class DefaultActionHandler extends AbstractActionHandler<DefaultAction, DefaultResult> {
	
	public DefaultActionHandler() {
		super(DefaultAction.class);
	}

	public DefaultActionHandler(Class<DefaultAction> actionType) {
		super(actionType);
	}

	@Override
	public DefaultResult execute(DefaultAction action, ExecutionContext context) throws DispatchException {
		log.info("DefaultActionHandler execute");
		log.info(action.getMessage());
		DefaultResult result = new DefaultResult();
		result.setResult("testtest");
		return result;
	}

	@Override
	public void rollback(DefaultAction action, DefaultResult result, ExecutionContext context)
			throws DispatchException {
		log.info("DefaultActionHandler rollback");
		
	}

	

}
