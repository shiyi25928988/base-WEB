package db.base.module;

import java.util.Properties;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import com.google.inject.name.Names;

import db.base.mapper.UserMapper;
import db.base.service.UserService;
import db.base.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class DataSourceModule extends MyBatisModule {
	
	public DataSourceModule() {
	}

	/* (non-Javadoc)
	 * @see org.mybatis.guice.AbstractMyBatisModule#initialize()
	 */
	@Override
	protected void initialize() {
		log.info("DataSourceModule initializing...");
		/*
		 * myBatisProperties is a property include JDBC 
		 * */
		Names.bindProperties(binder(), System.getProperties());
		bindDataSourceProviderType(PooledDataSourceProvider.class);
		bindTransactionFactoryType(JdbcTransactionFactory.class);
		
		bindService();
		addMapper();
	}
	
	/**
	 * configure bind services
	 */
	private void bindService() {
		bind(UserService.class).to(UserServiceImpl.class);
	}
	
	/**
	 * add mapper
	 */
	private void addMapper() {
		addMapperClass(UserMapper.class);
	}
	
}
