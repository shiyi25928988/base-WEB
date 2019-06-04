package base.filter;

import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import lombok.extern.java.Log;

@Log
public class SecureFilter extends ShiroFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	WebSecurityManager webSecurityManager;

	@Override
	public void init() throws Exception {
		super.init();
		log.info("SecureFilter init...");
		webSecurityManager = this.getSecurityManager();
	}
}
