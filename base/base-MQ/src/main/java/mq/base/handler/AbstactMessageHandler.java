package mq.base.handler;

import java.util.Objects;

import javax.jms.Message;

public abstract class AbstactMessageHandler implements IMessageHandler{

	private IMessageHandler nextHandler;
	
	@Override
	public void addHandler(IMessageHandler handler) {
		if(Objects.isNull(this.nextHandler)) {
			this.nextHandler = handler; 	
		} else {
			this.nextHandler.addHandler(handler);
		}
	}
	
	@Override
	public void onMessage(Message message) {
		if (this.handleMessage(message)) {
			return;
		} else {
			if (Objects.nonNull(nextHandler)) {
				this.nextHandler.onMessage(message);
			}
		}
	}

	protected abstract boolean handleMessage(Message message);

}
