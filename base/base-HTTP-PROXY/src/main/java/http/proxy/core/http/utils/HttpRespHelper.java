package http.proxy.core.http.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import base.common.json.JsonUtils;
import http.proxy.core.exception.UnsupportMIMETypeException;
import http.proxy.core.http.result.ReturnType;
import http.proxy.core.servlet.ServletHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public final class HttpRespHelper {

	/**
	 * To prevent instantiated.
	 */
	private HttpRespHelper() {
		throw new RuntimeException();
	}

	/**
	 * @param data
	 * @throws UnsupportMIMETypeException
	 * @throws IOException
	 */
	public static void sendResponseData(final Object data) throws UnsupportMIMETypeException, IOException {
		if (Objects.nonNull(data)) {
			if (data instanceof ReturnType) {
				sendResponseData((ReturnType<?>) data);
			} else {
				throw new UnsupportMIMETypeException();
			}
		} else {
			log.error("RestHelper#sendResponseData send a null object, cause of a void return type method");
			throw new NullPointerException();
		}
	}

	/**
	 * 
	 */
	public static void send404Status() {
		var resp = ServletHelper.getResponse();
		resp.setStatus(HttpStatusCode.SC_NOT_FOUND);
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer;
		try {
			writer = resp.getWriter();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * @param data
	 * @throws IOException
	 */
	private static void sendResponseData(final ReturnType<?> responseData) throws IOException {
		var resp = ServletHelper.getResponse();
		String data = "";
		if (responseData.getData() instanceof String) {
			data = (String) responseData.getData();
		} else {
			try {
				data = JsonUtils.toJson(responseData.getData());
			} catch (JsonProcessingException e) {
				log.error(e.getLocalizedMessage());
				resp.setStatus(HttpStatusCode.SC_INTERNAL_SERVER_ERROR);
				resp.setCharacterEncoding("UTF-8");
				PrintWriter writer = resp.getWriter();
				writer.write("Internal Service Error : " + e.getLocalizedMessage());
				writer.flush();
				writer.close();
				return;
			}
		}
		resp.setStatus(HttpStatusCode.SC_OK);
		resp.setContentType(responseData.getMimeType().getType());
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write(data);
		writer.flush();
		writer.close();
	}

	public static void sendHtml(String html) {
		try {
			HttpServletResponse resp = ServletHelper.getResponse();
			resp.setContentType(MimeType.TEXT_HTML.getType());
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(HttpStatusCode.SC_OK);
			PrintWriter writer;
			writer = resp.getWriter();
			writer.write(html);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * @param cssFileName
	 */
	public static void sendCss(String cssFileName) {
		log.info("requesting css file : " + cssFileName);
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator + "css" + File.separator
				+ cssFileName;
		File file = new File(filePath);
		if (file.exists()) {
			try {
				FileReader fis = new FileReader(file);
				long size = file.length();
				char[] temp = new char[(int) size];
				fis.read(temp, 0, (int) size);
				fis.close();

				HttpServletResponse resp = ServletHelper.getResponse();
				resp.setContentType(MimeType.TEXT_CSS.getType());
				resp.setCharacterEncoding("UTF-8");
				PrintWriter writer;
				writer = resp.getWriter();
				writer.write(temp);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * @param filePath
	 * @param imageType
	 * @throws IOException
	 */
	public static void sendImage(String filePath, MimeType imageType) throws IOException {
		HttpServletResponse response = ServletHelper.getResponse();
		File file = new File(filePath);

		if (file.exists()) {
			FileInputStream fis = new FileInputStream(file);
			long size = file.length();
			byte[] temp = new byte[(int) size];
			fis.read(temp, 0, (int) size);
			fis.close();
			byte[] data = temp;
			ServletOutputStream out = response.getOutputStream();
			response.setContentType(imageType.getType());
			out.write(data);
			out.flush();
			out.close();
		} else {
			throw new IOException(filePath + " doesn't exist!!");
		}
	}

	public static void sendBase64Icon(String icon) {
		try {
			HttpServletResponse resp = ServletHelper.getResponse();
			resp.setContentType(MimeType.IMAGE_PNG.getType());
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer;

			writer = resp.getWriter();

			writer.write(icon);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static Object getRequestPostBody(Class<?> clazz) throws IOException {
		HttpServletRequest request = ServletHelper.getRequest();
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return JsonUtils.fromJson(buffer, clazz);
	}

}
