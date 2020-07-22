package db.base.entity;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsEntity {

	String title;
	String url;
	String image;
	Date create_date;
	Date news_date;
	String content;
	String source;
}
