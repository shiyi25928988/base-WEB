package org.yshi.shared.action;

import net.customware.gwt.dispatch.shared.Action;

import org.yshi.shared.result.DefaultResult;

/**
 * @author yshi
 *
 */
public class DefaultAction implements Action<DefaultResult> {

	private String message;
	
	public DefaultAction() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
