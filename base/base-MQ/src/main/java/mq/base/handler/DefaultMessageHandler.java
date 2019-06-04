package mq.base.handler;

import javax.jms.Message;

public class DefaultMessageHandler extends AbstactMessageHandler{
	
	@Override
	protected boolean handleMessage(Message message) {
		return false;
	}

}
