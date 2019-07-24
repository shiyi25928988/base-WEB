package mq.base.handler;

import javax.jms.MessageListener;

public interface IMessageHandler extends MessageListener{

	void addHandler(IMessageHandler handler);

}
