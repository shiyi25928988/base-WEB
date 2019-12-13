package base.crawler.exceptions;

/**
 * @author yshi
 *
 */
public class AuthInfoInvalidException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthInfoInvalidException() {
		super();
	}
	
	public AuthInfoInvalidException(String message) {
		super(message);
	}
	
	public AuthInfoInvalidException(Exception message) {
		super(message);
	}
}
