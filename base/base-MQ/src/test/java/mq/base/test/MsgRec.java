package mq.base.test;

import java.util.Objects;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import mq.base.module.MessageQueueModule;
import mq.base.service.IMessageQueueService;

public class MsgRec {
	private static IMessageQueueService mqService;
	private static String topicName = "Csharp";
	
	public static void main(String...strings) {
		init();
		Topic topic = mqService.getTopic(topicName);
		
		Executors.newSingleThreadExecutor().submit(()->{
			MessageConsumer mc = mqService.getTopicMsgSubscriber(topic);
			try {
				mc.setMessageListener(message->{
					if(message instanceof TextMessage) {
						try {
							System.out.println(((TextMessage)message).getText().toString());
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

	public static void init() {
		Injector injector = Guice.createInjector(new MessageQueueModule());
		mqService = injector.getInstance(IMessageQueueService.class);
		
	}

	public void test() {
		
		Topic topic = mqService.getTopic(topicName);
		
		if(Objects.isNull(topic)) return;
		
		MessageConsumer mc = mqService.getTopicMsgSubscriber(topic);
		try {
			mc.setMessageListener(message->{
				System.out.println(message.toString());
			});
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
