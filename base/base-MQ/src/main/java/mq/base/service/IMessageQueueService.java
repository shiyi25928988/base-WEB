package mq.base.service;

import javax.jms.JMSException;

import mq.base.handler.IMessageHandler;
import mq.base.module.DestinationType;

/**
 * IMessageQueueService handles ...
 * 
 * @author (original): <a href="mailto:yshi@infor.com">yshi</a>
 * @author (most recent): $LastChangedBy$
 * @version: $LastChangedRevision$
 * @created: Jun 6, 2019
 * @modified: $LastChangedDate$
 */
public interface IMessageQueueService {
	
	void sendMsg(DestinationType destinationType, String destinationName, String msg);
	
	void addMessageHandler(DestinationType destinationType, String destinationName, IMessageHandler messageHandler);
	
	void close() throws JMSException;
	
}
