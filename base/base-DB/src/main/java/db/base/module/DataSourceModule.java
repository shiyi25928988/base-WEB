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
 * @author yshi
 *
 */
public class DataSourceModule extends MyBatisModule {
	private  Properties myBatisProperties;
	
	public DataSourceModule(Properties properties) {
		this.myBatisProperties = properties;
	}

	@Override
	protected void initialize() {
		Names.bindProperties(binder(), this.myBatisProperties);
		bindDataSourceProviderType(PooledDataSourceProvider.class);
		bindTransactionFactoryType(JdbcTransactionFactory.class);
		
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
