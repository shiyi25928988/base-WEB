package lego.rest.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import lego.rest.result.JSON;
import lego.servlet.ServletHelper;
import lombok.extern.slf4j.Slf4j;
import mq.base.utils.JsonUtils;

/**
 * @author yshi
 *
 */
@Slf4j
public final class RestHelper {
	
	/**
	 *  To prevent instantiated.
	 */
	private RestHelper() {
		throw new RuntimeException();
	}
	
	public static void sendResponseData(final Object data) {
		if(Objects.nonNull(data)) {
			if(data instanceof lego.rest.result.JSON) {
				sendResponseData((JSON<?>)data);
			}
		}
	}

	/**
	 * @param data
	 * @param resp
	 */
	private static void sendResponseData(final JSON<?> data) {
		if(Objects.isNull(data)) {
			throw new NullPointerException();
		}
		var resp = ServletHelper.getResponse();
		try {
			String json = JsonUtils.toJson(data.getObj());
			resp.setContentType(MimeType.APPLICATION_JSON.getType());
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
