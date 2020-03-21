package db.base.service;

import db.base.entity.User;

public interface UserService {

	User getUser(String userId);
	
	int insert(User user);

	//List<User> getAllUser();
}
