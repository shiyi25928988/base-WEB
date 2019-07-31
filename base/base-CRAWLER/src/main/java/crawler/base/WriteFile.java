package crawler.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.io.FileUtils;

public class WriteFile {
	
	
	public static void write(String url, String content){
		try {
			string2File(url+"\n\r"+content,"S:"+File.separator+"crawler_folder"+File.separator+System.currentTimeMillis()+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
