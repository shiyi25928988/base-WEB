package base.rest.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.google.inject.Inject;

import base.annotation.RestAPI;
import base.rest.utils.RestHelper;
import base.servlet.ServletHelper;
import cache.base.service.CacheService;
import db.base.entity.User;
import db.base.service.UserService;

/**
 * @author yshi
 *
 */
@RestAPI
public class UserController {

	@Inject
	UserService userService;

	@Inject
	CacheService<String, User> cacheService;

	@GET
	@Path(value = "/user")
	public void getUserByID() {
		HttpServletRequest req = ServletHelper.getRequest();
		HttpServletResponse resp = ServletHelper.getResponse();
		String userId = req.getParameter("USER_ID");

		Optional<User> opUser = cacheService.get(userId);

		if (opUser.isPresent()) {
			opUser.ifPresent(u -> {
				RestHelper.sendResponseData(u, resp);
			});
		} else {
			opUser = userService.getUser(userId);
			if (opUser.isPresent()) {
				User user = opUser.get();
				cacheService.put(userId, user);
				RestHelper.sendResponseData(user, resp);
			}
		}
	}

	@POST
	@Path(value = "/user")
	public void postUser() {
		// TODO
	}

	@PUT
	@Path(value = "/user")
	public void PUTUser() {
	}
}
