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

	/**
	 * @return
	 */
	Connection getConnection();
	
	Session getSession();
	
	Queue getQueue(String queueName);
	Topic getTopic(String topicName);
	
	Map<String, Queue> getQueues();
	Map<String, Topic> getTopics();
	
	void deleteQueue(String queueName) throws JMSException;
	void deleteTopic(String topicName) throws JMSException;
	
	MessageProducer getQueueMsgProducer(Queue queue);
	MessageProducer getTopicMsgPublisher(Topic topic);
	
	void sendQueueMsg(String queueName, Object msg);
	void sendTopicMsg(String topicName, Object msg);
	
	MessageConsumer getQueueMsgConsumer(Queue queue);
	MessageConsumer getTopicMsgSubscriber(Topic topic);
	
	void close() throws JMSException;
	
	IMessageHandler addMessageHandler(IMessageHandler messageHandler);
	
}
