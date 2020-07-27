package db.base.service;

import java.util.List;

import com.google.inject.Inject;

import db.base.entity.NewsEntity;
import db.base.mapper.NewsMapper;

public class NewsServiceImpl implements NewsService{
	
	@Inject
	NewsMapper newsMapper;

	@Override
	public int insertNews(NewsEntity newsEntity) {
		return newsMapper.insertNews(newsEntity);
	}

	@Override
	public boolean isNewsExist(NewsEntity newsEntity) {
		List<NewsEntity> list = newsMapper.getNewsByUrl(newsEntity);
		if(list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
