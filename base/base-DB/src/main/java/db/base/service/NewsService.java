package db.base.service;

import db.base.entity.NewsEntity;

public interface NewsService {

	int insertNews(NewsEntity newsEntity);
	
	boolean isNewsExist(NewsEntity newsEntity);
}
