package lego.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.google.inject.Inject;

import db.base.entity.User;
import db.base.service.UserService;
import html.base.page.LoginHtml;
import lego.annotation.RestAPI;
import lego.rest.result.HTML;
import lego.rest.result.JSON;
import lego.rest.utils.HtmlHelper;
import lego.servlet.ServletHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
@RestAPI
public class SecurityController {
	
	@Inject
	UserService userService;
	
	@GET
	@Path(value = "/sec/login")
	public HTML loginHtml() {
		log.info("get login page..");
		return new HTML(new LoginHtml(ServletHelper.getRequest().getContextPath()).render());
	}
	
	@POST
	@Path(value = "/sec/login")
	public JSON<String> login_post_username_password(@PathParam(value = "USER_NAME") String userName, @PathParam(value = "PASS_WORD") String passWord) {
		log.info("post login request..");
		///HttpServletRequest req = ServletHelper.getRequest();
		//HttpServletResponse resp = ServletHelper.getResponse();
		
//		String userName = req.getParameter("USER_NAME");
//		String passWord = req.getParameter("PASS_WORD");
		
		Subject currentUser = SecurityUtils.getSubject();
		/**  */
		if (!currentUser.isAuthenticated()) {
		    //collect user principals and credentials in a gui specific manner
		    //such as username/password html form, X509 certificate, OpenID, etc.
		    //We'll use the username/password example here since it is the most common.
		    //(do you know what movie this is from? ;)
		    UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
		    
		    /*
		     * this is all you have to do to support 'remember me' (no config - built in!):
		     **********************************/
		    token.setRememberMe(true);
		    try {
		    	currentUser.login(token);
		    }catch(AuthenticationException e) {
		    	log.error(e.getLocalizedMessage());
		    }
		}
		
		return new JSON<String>("success!!");
	}
	
	@GET
	@Path(value = "/logout")
	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
		
	}
}
