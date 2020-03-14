package lego.ioc;

/**
 * @author yshi
 *
 */
public interface RestApiService {
	
	/**
	 * HTTP GET
	 */
	void doGet();
	
	/**
	 * HTTP PUT
	 */
	void doPut();
	
	/**
	 * HTTP POST
	 */
	void doPost();
	
	/**
	 * HTTP DELETE
	 */
	void doDelete();
	
	void doHead();
	void doOptions();
	void doTrace();
}