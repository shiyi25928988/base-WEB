package base.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

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

	/**
	 * @param data
	 * @param resp
	 */
	public static void sendResponseData(final Object data, final HttpServletResponse resp) {
		if(Objects.isNull(data)) {
			throw new NullPointerException();
		}
		try {
			String json = JsonUtils.toJson(data);
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
