package db.base.service;

import java.util.List;
import java.util.Optional;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;

import db.base.entity.User;
import db.base.mapper.UserMapper;

/**
 * @author yshi
 *
 */
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;
    
    @Inject
    public void setUserMapper(UserMapper userMapper) {
      this.userMapper = userMapper;
    }
    
    @Transactional
    @Override
    public Optional<User> getUser(String userId) {
    	return this.userMapper.getUser(userId);
    	
    }

    @Transactional
	@Override
	public List<User> getAllUser() {
		return this.userMapper.getAllUser();
	}
}
