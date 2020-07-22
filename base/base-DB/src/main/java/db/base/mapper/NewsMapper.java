package db.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import db.base.entity.NewsEntity;
import db.base.entity.User;

@Mapper
public interface NewsMapper {

    @Select("SELECT * FROM news ")
    List<User> getAllNews();
    
    @Insert("INSERT INTO news (title,url,image,create_date,news_date,content,source) VALUES ("
    		+ "#{title},"
    		+ "#{url},"
    		+ "#{image},"
    		+ "#{create_date},"
    		+ "#{news_date},"
    		+ "#{content},"
    		+ "#{source}"
    		+ ")")
    int insertNews(NewsEntity news);
}
