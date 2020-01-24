package selenium.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author shiyi
 *
 */
public final class IOUtils {

	/**
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * @param folderPath
	 */
	public static void createFolder(String folderPath) {
		File f = new File(folderPath);
		f.setWritable(true);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	/**
	 * @param folderPath
	 * @param fileName
	 */
	public static void creatEmptyFile(String folderPath, String fileName) {
		if (!isFileExist(folderPath)) {
			createFolder(folderPath);
		}

		String filePath;
		if (folderPath.endsWith(File.separator)) {
			filePath = folderPath + fileName;
		} else {
			filePath = folderPath + File.separator + fileName;
		}
		try (var fileOutputStream = new FileOutputStream(filePath, true);
				var fileChannel = fileOutputStream.getChannel()) {
			ByteBuffer byteBuff = ByteBuffer.wrap("".getBytes());
			byteBuff.put("".getBytes());
			byteBuff.flip();
			fileChannel.write(byteBuff);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
