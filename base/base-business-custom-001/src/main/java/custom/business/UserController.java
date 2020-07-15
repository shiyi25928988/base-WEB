package custom.business;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.inject.Inject;

import cache.base.service.CacheService;
import db.base.entity.User;
import db.base.service.UserService;
import lego.annotation.RequestBody;
import lego.annotation.Controller;
import lego.rest.result.JSON;
import lego.rest.result.Result;

/**
 * @author yshi
 *
 */
@Controller
public class UserController {

	@Inject
	UserService userService;
	
	@Inject
	CacheService<String, User> cacheService;

	@GET
	@Path(value = "/test/user")
	public JSON<User> getUserByID(@PathParam(value = "USER_ID") String userId) {
		User user = userService.getUser(userId);
		return new JSON<User>(user);
	}

}