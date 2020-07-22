package db.base.service;

import com.google.inject.Inject;

import db.base.entity.NewsEntity;
import db.base.mapper.NewsMapper;

public class NewsServiceImpl implements NewsService{
	
	@Inject
	NewsMapper newsMapper;

	@Override
	public int insertNews(NewsEntity newsEntity) {
		// TODO Auto-generated method stub
		return newsMapper.insertNews(newsEntity);
	}

}
