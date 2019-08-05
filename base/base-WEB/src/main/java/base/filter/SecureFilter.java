package base.filter;

import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class SecureFilter extends ShiroFilter{

	WebSecurityManager webSecurityManager;

	/* (non-Javadoc)
	 * @see org.apache.shiro.web.servlet.ShiroFilter#init()
	 */
	@Override
	public void init() throws Exception {
		super.init();
		log.info("SecureFilter init...");
		webSecurityManager = this.getSecurityManager();
	}
}
