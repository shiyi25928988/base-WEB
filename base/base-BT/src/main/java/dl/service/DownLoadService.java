package dl.service;

import com.google.inject.ImplementedBy;

@ImplementedBy(DownLoadServiceImpl.class)
public interface DownLoadService {
	
	void download(String magnetUrl);
	
	DownLoadStatus getDownLoadStatus(String magnetUrl);
}
