package mq.base.service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import com.google.inject.Inject;

import lombok.extern.java.Log;
import mq.base.handler.DefaultMessageHandler;
import mq.base.handler.IMessageHandler;

@Log
public class MessageQueueServiceImpl implements IMessageQueueService {

	ConcurrentHashMap<String, Queue> queues = new ConcurrentHashMap<>();
	ConcurrentHashMap<String, Topic> topics = new ConcurrentHashMap<>();
	
	private IMessageHandler handler;
	
	private final Connection connection;

	private final Session session;
	
	@Inject
	public MessageQueueServiceImpl(Connection connection, Session session) {
		this.connection = connection;
		this.session = session;
	}
	
	@Override
	public Connection getConnection() {
		if (Objects.isNull(connection))
			throw new NullPointerException();
		return connection;
	}

	@Override
	public Session getSession() {
		if (Objects.isNull(session))
			throw new NullPointerException();
		return session;
	}

	@Override
	public Queue getQueue(String queueName) {
		Queue queue = queues.get(queueName);
		if (Objects.nonNull(queue)) {
			return queue;
		} else {
			try {
				queue = getSession().createQueue(queueName);
				queues.put(queueName, queue);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return queue;
	}

	@Override
	public Topic getTopic(String topicName) {
		Topic topic = topics.get(topicName);
		if (Objects.nonNull(topic)) {
			return topic;
		} else {
			try {
				topic = getSession().createTopic(topicName);
				topics.put(topicName, topic);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return topic;
	}

	@Override
	public Map<String, Queue> getQueues() {
		return queues;
	}

	@Override
	public Map<String, Topic> getTopics() {
		return topics;
	}

	@Override
	public void deleteQueue(String queueName) throws JMSException {
		queues.remove(queueName);
	}

	@Override
	public void deleteTopic(String topicName) throws JMSException {
		topics.remove(topicName);
	}

	@Override
	public void sendQueueMsg(String queueName, Object msg) {
		if (msg instanceof String) {
			try {
				getQueueMsgProducer(getQueue(queueName)).send(getSession().createTextMessage(msg.toString()));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		//TODO

	}

	@Override
	public void sendTopicMsg(String topicName, Object msg) {
		if (msg instanceof String) {
			try {
				getTopicMsgPublisher(getTopic(topicName)).send(getSession().createTextMessage(msg.toString()));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		//TODO

	}

	@Override
	public MessageProducer getQueueMsgProducer(Queue queue) {
		try {
			return getSession().createProducer(queue);
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MessageProducer getTopicMsgPublisher(Topic topic) {
		try {
			return getSession().createProducer(topic);
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MessageConsumer getQueueMsgConsumer(Queue queue) {
		if(Objects.nonNull(session)) {
			try {
				MessageConsumer messageConsumer = session.createConsumer(queue);
				return messageConsumer;
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public MessageConsumer getTopicMsgSubscriber(Topic topic) {
		if(Objects.nonNull(session)) {
			try {
				return session.createConsumer(topic);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void close() throws JMSException {
		if(Objects.nonNull(connection)) {
			connection.close();
		}
		if(Objects.nonNull(session)) {
			session.close();
		}
	}

	@Override
	public IMessageHandler addMessageHandler(IMessageHandler handler) {
		if(Objects.isNull(handler)) {
			handler = new DefaultMessageHandler();
			handler.addHandler(handler);
		}else {
			handler.addHandler(handler);
		}
		return handler;
	}

}
