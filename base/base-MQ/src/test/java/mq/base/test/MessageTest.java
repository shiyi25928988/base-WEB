package mq.base.test;

import java.util.Objects;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import mq.base.handler.TextMessageHandler;
import mq.base.module.MessageQueueModule;
import mq.base.service.IMessageQueueService;

public class MessageTest {

	private static IMessageQueueService mqService;
	private static String queueName = "any_queue";
	private static String msgContent = "test2";

	@BeforeAll
	public static void init() {
		Injector injector = Guice.createInjector(new MessageQueueModule());
		mqService = injector.getInstance(IMessageQueueService.class);
	}

	@Test
	public void test() {
		Assertions.assertTrue(Objects.nonNull(mqService));
		mqService.addMessageHandler(new TextMessageHandler());
		mqService.sendQueueMsg(queueName, msgContent);
		MessageConsumer messageConsumer = null;
		messageConsumer = mqService.getQueueMsgConsumer(mqService.getQueue(queueName));
		Assertions.assertTrue(Objects.nonNull(messageConsumer));
		
		//messageConsumer.setMessageListener();
		
		Message message = null;
		try {
			message = messageConsumer.receive();
			Assertions.assertTrue(message instanceof TextMessage);
			Assertions.assertTrue(((TextMessage)message).getText().equals(msgContent));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void after() {
		try {
			mqService.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
