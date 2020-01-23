package selenium.base.exception;

/**
 * @author shiyi
 *
 */
public class ScriptParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScriptParseException() {
		super();
	}

	public ScriptParseException(String exception) {
		super(exception);
	}

	public ScriptParseException(Exception exception) {
		super(exception);
	}
}
