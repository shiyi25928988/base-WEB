package mq.base.module;

import java.util.Hashtable;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Nonnull;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.qpid.jms.jndi.JmsInitialContextFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import lombok.extern.java.Log;
import mq.base.service.IMessageQueueService;
import mq.base.service.MessageQueueServiceImpl;

@Log
public class MessageQueueModule extends AbstractModule {

	private static Hashtable<String, String> environment = new Hashtable<>();
	private static final String CONNECTION_FACTORY_PREFIX = "connectionfactory.";
	private static final String CONNECTION_FACTORY_LOOKUP_NAME = CONNECTION_FACTORY_PREFIX;//"BrokerURL";
	
	private static String BROKER_URL = "";// TODO
	private static String BROKER_UESRNAME = "";// TODO
	private static String BROKER_PASSWORD = "";// TODO

	public MessageQueueModule() {}
	
	public MessageQueueModule(Properties properties) {
		BROKER_URL = properties.getProperty("BROKER_url"); // amqps://b-6cfc4ea2-8ff6-4a62-8197-4b36395b0169-1.mq.ap-northeast-1.amazonaws.com:5671
		BROKER_UESRNAME = properties.getProperty("BROKER_username"); // username = sunsystems123
		BROKER_PASSWORD = properties.getProperty("BROKER_password"); // password = sunsystems123
		environment.put(CONNECTION_FACTORY_PREFIX + CONNECTION_FACTORY_LOOKUP_NAME, BROKER_URL);
	}

	@Override
	protected void configure() {
		bind(Context.class).toProvider(ContextProvider.class).in(Singleton.class);
		bind(PooledConnectionFactory.class).toProvider(PooledConnectionFactoryProvider.class).in(Singleton.class);
		/*-----------------*/
		bind(Connection.class).toProvider(ConnectionProvider.class);
		bind(Session.class).toProvider(SessionProvider.class);
		bind(IMessageQueueService.class).toProvider(MessageQueueServiceProider.class);
	}

	public static class ContextProvider implements Provider<Context> {
		@Override
		public Context get() {
			return getContext();
		}

		private static Context getContext() {
			JmsInitialContextFactory jmsInitialContextFactory = new JmsInitialContextFactory();
			Context context = null;
			try {
				context = jmsInitialContextFactory.getInitialContext(environment);
			} catch (NamingException e) {
				e.printStackTrace();
			}
			return context;
		}
	}

	public static class PooledConnectionFactoryProvider implements Provider<PooledConnectionFactory> {
		@Inject
		@Nonnull
		Context context;

		@Override
		public PooledConnectionFactory get() {
			try {
				PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
				pooledConnectionFactory
						.setConnectionFactory((ConnectionFactory) context.lookup(CONNECTION_FACTORY_LOOKUP_NAME));
				pooledConnectionFactory.start();
				return pooledConnectionFactory;
			} catch (NamingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public static class ConnectionProvider implements Provider<Connection> {

		@Inject
		@Nonnull
		PooledConnectionFactory pooledConnectionFactory;

		@Override
		public Connection get() {
			try {
				Connection connection = pooledConnectionFactory.createConnection(BROKER_UESRNAME, BROKER_PASSWORD);
				connection.setExceptionListener(e -> log.severe(e.getMessage()));
				connection.start();
				return connection;
			} catch (JMSException e) {
				log.severe(e.toString());
				e.printStackTrace();
			}
			return null;
		}
	}

	public static class SessionProvider implements Provider<Session> {
		@Inject
		@Nonnull
		Connection connection;

		@Override
		public Session get() {
			try {
				return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			} catch (JMSException e) {
				log.severe(e.toString());
				throw new NullPointerException();
			}
		}
	}

	public static class MessageQueueServiceProider implements Provider<IMessageQueueService> {
		@Inject
		@Nonnull
		Connection connection;

		@Inject
		@Nonnull
		Session session;

		@Override
		public IMessageQueueService get() {
			return new MessageQueueServiceImpl(connection, session);
		}
	}
}
