package base.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import base.crawler.config.CrawlerConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public abstract class WriteFile {
	
	public static void writeResult(CrawlResults result){
		var content = result.getContent();
		var extension = result.getExtension();
		writeTOfile(content, 
				CrawlerConstants.CURRENT_PATH + File.separator + UUID.randomUUID() + extension);
	}
	
	public static void writeTOfile(byte[] source, String filePath) {
         try {
			Files.write(Path.of(filePath), source, StandardOpenOption.CREATE);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * @param res
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static boolean string2File(String res, String filePath) throws IOException {  
		
        boolean flag = true;  
        BufferedReader bufferedReader = null;  
        BufferedWriter bufferedWriter = null;  
        try {  
            File distFile = new File(filePath);  
            if (!distFile.getParentFile().exists()) {
                distFile.getParentFile().mkdirs();  
            }  
            bufferedReader = new BufferedReader(new StringReader(res));  
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));  
            char buf[] = new char[1024]; 
            int len;  
            while ((len = bufferedReader.read(buf)) != -1) {  
                bufferedWriter.write(buf, 0, len);  
            }  
            bufferedWriter.flush();  
            bufferedReader.close();  
            bufferedWriter.close();  
        } catch (IOException e) {  
            flag = false;  
            throw e;
        }  
        return flag;  
    }  
	
}
