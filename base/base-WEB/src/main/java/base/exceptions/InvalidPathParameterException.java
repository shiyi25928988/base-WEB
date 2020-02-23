package base.exceptions;

/**
 * @author shiyi
 *
 */
public class InvalidPathParameterException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPathParameterException() {
		super();
	}
	
	public InvalidPathParameterException(String message) {
		super(message);
	}
}
