package core.module;


import javax.servlet.ServletContext;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class SecurityModule extends ShiroWebModule {

	@SuppressWarnings("unused")
	private ServletContext context;

	/**
	 * @param context
	 */
	SecurityModule(ServletContext context) {
		super(context);
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.guice.web.ShiroWebModule#configureShiroWeb()
	 */
	@Override
	protected void configureShiroWeb() {
		log.info("configureShiroWeb...");
		
		bindRealm().toInstance(getRealm());
		
		/*
		 * add the URL template and authenticate type to the filterChain
		 * 
		 * */
		addFilterChain("/sec/login", ShiroWebModule.ANON);
//		addFilterChain("/logout", ShiroWebModule.AUTHC);
//		addFilterChain("/user", ShiroWebModule.AUTHC);
	}

	/**
	 * @return BasicDataSource
	 */
	private BasicDataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/base?serverTimezone=UTC&characterEncoding=utf-8");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}

	/**
	 * @return Realm
	 */
	private Realm getRealm() {
		JdbcRealm realm = new JdbcRealm();
		realm.setDataSource(getDataSource());
		realm.setAuthenticationQuery("SELECT password FROM sec_user WHERE username = ?");
		realm.setUserRolesQuery("SELECT r.role_name FROM sec_user u, sec_user_role ur, sec_role r WHERE u.id = ur.user_id AND r.id = ur.role_id AND u.username = ?");
		realm.setPermissionsQuery("SELECT p.permission_name FROM sec_role r, sec_role_permission rp, sec_permission p WHERE r.id = rp.role_id AND p.id = rp.permission_id AND r.role_name = ?");
		realm.setPermissionsLookupEnabled(true);
		return realm;
	}
	
	

}
