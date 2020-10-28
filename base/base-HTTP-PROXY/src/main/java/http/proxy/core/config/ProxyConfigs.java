package http.proxy.core.config;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProxyConfigs {

	@JsonProperty("CONFIG_LIST")
	private List<ProxyConfig> configList = new ArrayList<>();
	
	public void add(ProxyConfig proxyConfig) {
		this.configList.add(proxyConfig);
	}
	public List<ProxyConfig> getProxyConfigList() {
		return this. configList;
	}
}
