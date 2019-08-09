package org.yshi.shared.result;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author yshi
 *
 */
public class DefaultResult implements Result {

	private String result;
	
	public DefaultResult() {}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
