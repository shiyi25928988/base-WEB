package db.base.service;

import java.util.List;
import java.util.Optional;

import db.base.entity.User;

public interface UserService {

	Optional<User> getUser(String userId);

	List<User> getAllUser();
}
