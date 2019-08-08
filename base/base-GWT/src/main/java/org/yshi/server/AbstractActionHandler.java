package org.yshi.server;

import org.yshi.shared.AbstractAction;
import org.yshi.shared.AbstractResult;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

/**
 * @author yshi
 *
 */
public abstract class AbstractActionHandler implements net.customware.gwt.dispatch.server.ActionHandler<AbstractAction, AbstractResult>{

	/* (non-Javadoc)
	 * @see net.customware.gwt.dispatch.server.ActionHandler#getActionType()
	 */
	@Override
	public abstract Class<AbstractAction> getActionType();

	/* (non-Javadoc)
	 * @see net.customware.gwt.dispatch.server.ActionHandler#execute(net.customware.gwt.dispatch.shared.Action, net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	@Override
	public abstract AbstractResult execute(AbstractAction action, ExecutionContext context) throws DispatchException;

	/* (non-Javadoc)
	 * @see net.customware.gwt.dispatch.server.ActionHandler#rollback(net.customware.gwt.dispatch.shared.Action, net.customware.gwt.dispatch.shared.Result, net.customware.gwt.dispatch.server.ExecutionContext)
	 */
	@Override
	public abstract void rollback(AbstractAction action, AbstractResult result, ExecutionContext context)
			throws DispatchException ;

}
