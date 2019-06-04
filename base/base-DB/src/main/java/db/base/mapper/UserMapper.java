package db.base.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import db.base.entity.User;


@Mapper
public interface UserMapper {
	
    @Select("SELECT "
    		+ "USER_ID as userId, "
    		+ "USER_NAME as userName "
    		+ "FROM user "
    		+ "WHERE USER_ID = #{userId}")
    Optional<User> getUser(@Param("userId") String userId);
    
    
    @Select("SELECT * FROM user ")
    List<User> getAllUser();
}
