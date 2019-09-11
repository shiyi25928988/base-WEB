package base.rest.utils;

/**
 * @author yshi
 *
 */
public interface RestService {
	
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
