package mq.base.handler;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.jms.TextMessage;

import lombok.extern.java.Log;

@Log
public class TextMessageHandler extends AbstactMessageHandler {

	List<TextMessage> list = new ArrayList<>();

	@Override
	protected boolean handleMessage(Message message) {
		log.info("handleMessage");
		if (message instanceof TextMessage) {
			list.add((TextMessage) message);
			//TODO
			return true;
		}

		return false;
	}

}
