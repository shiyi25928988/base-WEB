package mq.base.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MessageWrapper {
	
	private String typeName;
	
	private String content;

}
