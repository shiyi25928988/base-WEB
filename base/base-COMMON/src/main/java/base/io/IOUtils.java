package base.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author yshi
 *
 */
public abstract class IOUtils {

	/**
	 * @param content
	 * @param dir
	 * @param fileName
	 */
	public static void writeFile(byte[] content, String dir, String fileName) {
		
		System.out.println(dir);
		System.out.println(fileName);
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
		System.out.println(filePath);
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

	/**
	 * @param content
	 * @param dir
	 * @param fileName
	 */
	public static void writeStringFile(String content, String dir, String fileName) {

		writeFile(content.getBytes(), dir, fileName);

	}
	
}
