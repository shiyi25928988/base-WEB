package base.crawler.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Objects;

import base.crawler.CrawlResults;
import db.base.entity.NewsEntity;
import db.base.service.NewsService;

public class DbWriter {

	private NewsService newService;
	public DbWriter(NewsService newService) {
		this.newService = newService;
	}
	
	public synchronized int write(CrawlResults result) throws UnsupportedEncodingException {
		if(Objects.isNull(result)) {
			return 0;
		}
		
		NewsEntity newsEntity = new NewsEntity();
		newsEntity.setTitle(result.getTitle());
		newsEntity.setImage(result.getImageUrl());
		newsEntity.setUrl(result.getUrl());
		newsEntity.setContent(new String(result.getContent(), result.getCharSet()));
		newsEntity.setCreate_date(new Date());
		newsEntity.setSource(result.getSource());
		newsEntity.setNews_date(result.getReleaseDate());
		return newService.insertNews(newsEntity);
	}
}
