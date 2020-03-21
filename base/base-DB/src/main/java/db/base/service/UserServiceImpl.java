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
    public User getUser(String userId) {
    	return this.userMapper.getUser(userId).get();
    }

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return this.userMapper.insert(user);
		
	}

//    @Transactional
//	@Override
//	public List<User> getAllUser() {
//		//return this.userMapper.getAllUser();
//    	return this.userMapper.;
//	}
}
