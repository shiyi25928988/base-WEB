package base.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import mq.base.utils.JsonUtils;

public final class RestHelper {
	
	private RestHelper() {
		throw new RuntimeException();
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
