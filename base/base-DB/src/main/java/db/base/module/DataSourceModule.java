package db.base.module;

import java.util.Properties;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import com.google.inject.name.Names;

import db.base.mapper.UserMapper;
import db.base.service.UserService;
import db.base.service.UserServiceImpl;

/**
 * 
 * install this module to RootModule.java
 * 
 * 
 * */

public class DataSourceModule extends MyBatisModule {

	/**
	 * JDBC configuration
	 * 
	 * */
	private final static Properties getConnectionProperties(String schema) {
		Properties myBatisProperties = new Properties();
	    myBatisProperties.setProperty("JDBC.driver", "com.mysql.cj.jdbc.Driver");
	    myBatisProperties.setProperty("JDBC.url", "jdbc:mysql://localhost:3306/" + schema + "?serverTimezone=UTC&characterEncoding=utf-8");
		myBatisProperties.setProperty("JDBC.schema", schema);
		myBatisProperties.setProperty("JDBC.username", "root");
		myBatisProperties.setProperty("JDBC.password", "root");
		myBatisProperties.setProperty("JDBC.autoCommit", "false");
		return myBatisProperties;
	}

	@Override
	protected void initialize() {
		
		environmentId("development");
		
		Names.bindProperties(binder(), getConnectionProperties("base"));
		
		bindDataSourceProviderType(PooledDataSourceProvider.class);
		
		bindTransactionFactoryType(JdbcTransactionFactory.class);
		
		/**
		 * 
		 * 
		 * */
		bindService();
		
		addMapper();
		
	}
	
	private void bindService() {
		bind(UserService.class).to(UserServiceImpl.class);
	}
	
	private void addMapper() {
		addMapperClass(UserMapper.class);
	}
	

}
