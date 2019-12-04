package base.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author yshi
 *
 */
public abstract class IOUtils {
	
	public static void writeBinaryToFile() {}
	
	/**
	 * @param content
	 * @param filePath
	 * @param charset
	 * @throws IOException
	 */
	public static void writeStringToFile(String content, String filePath, String charset) throws IOException {
		Charset cs = Charset.forName(charset);
		writeStringToFile(content, filePath, cs);
	}
	
	/**
	 * @param content
	 * @param filePath
	 * @param charset
	 * @throws IOException
	 */
	public static void writeStringToFile(String content, String filePath, Charset charset) throws IOException {
		Path path = FileSystems.getDefault().getPath(filePath);
		writeStringToFile(content, path, charset);
	}
	

	/**
	 * @param content
	 * @param filePath
	 * @param charset
	 * @throws IOException
	 */
	public static void writeStringToFile(String content, Path filePath, Charset charset) throws IOException {
		BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath, charset);
		bufferedWriter.write(content, 0, content.length());
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	/**
	 * @param content
	 * @param dir
	 * @param fileName
	 */
	public static void writeBinaryFile(byte[] content, String dir, String fileName) {
		
		var f = new File(dir);
		f.setWritable(true);
		if (!f.exists()) {
			f.mkdirs();
		}

		String filePath;
		if (dir.endsWith(File.separator)) {
			filePath = dir + fileName;
		} else {
			filePath = dir + File.separator + fileName;
		}
		try (var fileOutputStream = new FileOutputStream(filePath, true);
				var fileChannel = fileOutputStream.getChannel()) {
			ByteBuffer byteBuff = ByteBuffer.wrap(content);
			byteBuff.put(content);
			byteBuff.flip();
			fileChannel.write(byteBuff);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
