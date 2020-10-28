package http.proxy.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import base.common.io.IOUtils;
import base.common.json.JsonUtils;
import http.proxy.core.annotation.PropertiesFile;
import http.proxy.core.booter.ServiceBooter;
import http.proxy.core.config.PathMapRegister;
import http.proxy.core.config.ProxyConfig;
import http.proxy.core.config.ProxyConfigs;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@PropertiesFile(files = { "application.properties" })
@Slf4j
public class Main {

	public static void main(String... strings) {
		try {
			ServiceBooter.startFrom(http.proxy.core.Main.class);
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
	}
}
