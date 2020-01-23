package selenium.base.exception;

/**
 * @author shiyi
 *
 */
public class ScriptFileNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScriptFileNotExistException() {
		super();
	}

	public ScriptFileNotExistException(String exception) {
		super(exception);
	}

	public ScriptFileNotExistException(Exception exception) {
		super(exception);
	}
}
