package selenium.base.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;
import selenium.base.script.ScriptLineObject;

/**
 * @author shiyi
 *
 */
@Slf4j
public final class IOUtils {
	
	/**
	 * @param filePath
	 * @return
	 */
	public static Queue<ScriptLineObject> readLines(String filePath) throws FileNotFoundException{
		int line = 0;
		Queue<ScriptLineObject> queue = new LinkedBlockingQueue<>(); 
		if(!isFileExist(filePath)) {
			throw new FileNotFoundException(filePath + " not exist!");
		}
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String str;
		try {
			while((str = reader.readLine()) != null) {
				line++;
				queue.add(new ScriptLineObject(str, line));
			}
		} catch (IOException e) {
			log.error(e.toString());
		}
		return queue;
	}

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
