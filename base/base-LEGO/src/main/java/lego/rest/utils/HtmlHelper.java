package lego.rest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lego.servlet.ServletHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public final class HtmlHelper {

	/**
	 * @param html
	 */
	public static void sendHtmlPage(String html) {
		try {
			HttpServletResponse resp = ServletHelper.getResponse();
			resp.setContentType(MimeType.TEXT_HTML.getType());
			resp.setCharacterEncoding("UTF-8");
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
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator +"css" + File.separator + cssFileName;
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
			}catch (IOException e) {
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

}
