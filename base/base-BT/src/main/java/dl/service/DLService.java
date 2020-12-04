package dl.service;

import com.google.inject.ImplementedBy;

@ImplementedBy(DLServiceImpl.class)
public interface DLService {

	void download(String magnet);
}
