package selenium.base.script;

/**
 * @author 86135
 *
 */
public class ScriptLineObject {

	private String lineContent;
	private int lineNum;
	
	public ScriptLineObject(String lineContent, int lineNum){
		this.lineContent = lineContent;
		this.lineNum = lineNum;
	}

	public String getLineContent() {
		return lineContent;
	}

	public int getLineNum() {
		return lineNum;
	}
	
}
