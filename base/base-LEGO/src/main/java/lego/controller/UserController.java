package lego.controller;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.inject.Inject;

import cache.base.service.CacheService;
import db.base.entity.User;
import db.base.service.UserService;
import lego.annotation.RestAPI;

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
	public JSON<User> getUserByID(@PathParam(value = "USER_ID") String userId) {
		User user = new User();
		Optional<User> opUser = userService.getUser(userId);
		if (opUser.isPresent()) {
			user = opUser.get();
		}
		return new JSON<User>(user);
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
