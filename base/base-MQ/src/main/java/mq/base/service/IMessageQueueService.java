package mq.base.service;

import java.util.Map;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import mq.base.handler.IMessageHandler;
import mq.base.module.AckMode;
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
