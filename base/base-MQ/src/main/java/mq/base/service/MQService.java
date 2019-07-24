package mq.base.service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.google.inject.Inject;

import lombok.extern.java.Log;
import mq.base.handler.IMessageHandler;
import mq.base.module.DestinationType;

@Log
public class MQService implements IMessageQueueService {
	
	private final Connection connection;
	
	private ConcurrentLinkedQueue<Session> sessionPool = new ConcurrentLinkedQueue<>();
	
	private List<MessageConsumer> consumerList = new CopyOnWriteArrayList<>();
	
	@Inject
	public MQService(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void sendMsg(DestinationType destinationType, String destinationName, String msg) {
		
		Session session = getSession();
		
		switch(destinationType) {
		case Queue:
			try(MessageProducer producer = session.createProducer(session.createQueue(destinationName))) {
				producer.send(session.createTextMessage(msg));
			} catch (JMSException e) {
				e.printStackTrace();
			}
			break;
		case Topic:
			try(MessageProducer producer = session.createProducer(session.createTopic(destinationName))) {
				producer.send(session.createTextMessage(msg));
			} catch (JMSException e) {
				e.printStackTrace();
			}
			break;
		}
		
		sessionPool.offer(session);
		
	}

	@Override
	public void close() throws JMSException {
		
		consumerList.forEach(consumer->{
			try {
				consumer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		});
		
		sessionPool.forEach(session->{
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void addMessageHandler(DestinationType destinationType, String destinationName, IMessageHandler messageHandler) {
		Session session = getSession();
		
		MessageConsumer messageConsumer = null;
		
		switch(destinationType) {
		case Queue:
			try {
				messageConsumer = session.createConsumer(session.createQueue(destinationName));
				messageConsumer.setMessageListener(messageHandler);
			} catch (JMSException e) {
				e.printStackTrace();
			}
			break;
		case Topic:
			try {
				messageConsumer = session.createConsumer(session.createTopic(destinationName));
				messageConsumer.setMessageListener(messageHandler);
			} catch (JMSException e) {
				e.printStackTrace();
			}
			break;
		}
		consumerList.add(messageConsumer);
		sessionPool.offer(session);
		
	}

	private synchronized Session getSession() {
		
		Session session = null;
		
		do {
			if(sessionPool.isEmpty()) {
				try {
					session = this.connection.createSession(Session.AUTO_ACKNOWLEDGE);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}else {
				session = sessionPool.poll();
				if(Objects.isNull(session)) {
					try {
						session = this.connection.createSession(Session.AUTO_ACKNOWLEDGE);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		}while(Objects.nonNull(session));
		
		return session;
	}
}
