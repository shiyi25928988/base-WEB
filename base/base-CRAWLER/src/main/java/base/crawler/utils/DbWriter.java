package base.crawler.utils;

import java.util.Date;
import java.util.Objects;

import base.crawler.CrawlResults;
import db.base.entity.NewsEntity;
import db.base.service.NewsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class DbWriter {

	private NewsService newService;

	public DbWriter(NewsService newService) {
		this.newService = newService;
	}

	public synchronized int write(CrawlResults result) throws Exception {
		if (Objects.isNull(result)) {
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
		newsEntity.setKey_word(result.getKeyWord());
		newsEntity.setType(result.getContentType());

		try {
			if(!this.isExist(newsEntity)) {
				return newService.insertNews(newsEntity);
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
		}
		return 0;
	}

	public boolean isExist(NewsEntity newsEntity) {
		return newService.isNewsExist(newsEntity);
	}
}
