package base.dl.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DownLoadServiceImpl implements DownLoadService{

	@Inject
	@Named("application.fileStoragePath")
	String storagePath;
	
	@Override
	public void download(String magnetUrl) {
		System.out.println(System.getProperty("application.fileStoragePath", "sss"));
		System.out.println(storagePath);
	}

	@Override
	public DownLoadStatus getDownLoadStatus(String magnetUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
