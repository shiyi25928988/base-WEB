package db.base.service;

import com.google.inject.ImplementedBy;

import db.base.entity.NewsEntity;


public interface NewsService {

	int insertNews(NewsEntity newsEntity);
}
