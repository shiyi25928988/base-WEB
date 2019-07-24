package mq.base.handler;

import java.util.Objects;
import java.util.logging.Level;

import javax.jms.Message;

import lombok.extern.java.Log;

/**
 * @author yshi
 *
 */
@Log
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
			log.log(Level.FINE, message.toString());
		} else {
			if (Objects.nonNull(nextHandler)) {
				this.nextHandler.onMessage(message);
			} else {
				log.log(Level.WARNING, message.toString()+ " not handled correct.");
			}
		}
	}

	protected abstract boolean handleMessage(Message message);

}
