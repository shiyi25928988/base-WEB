package selenium.base.exception;

public class UnsupportedBrowserException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedBrowserException() {
		super();
	}

	public UnsupportedBrowserException(String exception) {
		super(exception);
	}

	public UnsupportedBrowserException(Exception exception) {
		super(exception);
	}
}
