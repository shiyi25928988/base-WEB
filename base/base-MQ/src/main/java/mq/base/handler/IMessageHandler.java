package mq.base.handler;

import javax.jms.Message;
import javax.jms.MessageListener;

public interface IMessageHandler extends MessageListener{

	void addHandler(IMessageHandler handler);
	
	default void failedMessage(Message message) {
		//TODO to process the message which cannot handled by the handlers
	}
}
