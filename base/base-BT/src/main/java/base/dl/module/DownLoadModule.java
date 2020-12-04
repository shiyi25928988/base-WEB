package base.dl.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.Config;

public class DownLoadModule extends AbstractModule{

	
	
	@Override
	protected void configure() {
		DHTModule DHTModule = new DHTModule();
		//install(DHTModule);
		Names.bindProperties(binder(), System.getProperties());
		bind(Config.class).toProvider(ConfigProvider.class).in(Singleton.class);
		bind(DHTConfig.class).toProvider(DHTConfigProvider.class).in(Singleton.class);
		bind(DHTModule.class).toInstance(DHTModule);
	}
	
	public static class ConfigProvider implements Provider<Config>{
		@Override
		public Config get() {
			Config config = new Config() {
			    @Override
			    public int getNumOfHashingThreads() {
			        return Runtime.getRuntime().availableProcessors() * 2;
			    }
			};
			return config;
		}
	}
	
	public static class DHTConfigProvider implements Provider<DHTConfig> {

		@Override
		public DHTConfig get() {
			DHTConfig DHTConfig = new DHTConfig() {
			    @Override
			    public boolean shouldUseRouterBootstrap() {
			        return true;
			    };
			};
			return DHTConfig;
		}
		
	}
	
	public static class DHTModuleProvider implements Provider<DHTModule> {

		@Inject
		DHTConfig dhtConfig;
		
		@Override
		public DHTModule get() {
			DHTModule dhtModule = new DHTModule(dhtConfig);
			return dhtModule;
		}
	}
}
