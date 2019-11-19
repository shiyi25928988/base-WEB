package base.crawler.exceptions;

public class ExtendTypeNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExtendTypeNotFoundException() {
		super();
	}
	
	public ExtendTypeNotFoundException(String message) {
		super(message);
	}
	
	public ExtendTypeNotFoundException(Exception e) {
		super(e);
	}
}
