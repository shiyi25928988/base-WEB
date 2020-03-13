package security;

import lego.annotation.PackageScan;
import lego.ioc.PackageScanConfig;

@PackageScan()
public class Config {
	
	static {
		PackageScanConfig.accept(Config.class);
	}
}
