package selenium.base.command;

import selenium.base.script.ICommand;

public abstract class AbstractCommand implements ICommand{

	private String scriptFileName;
	private int lineNum;

	public String getScriptFileName() {
		return scriptFileName;
	}

	public void setScriptFileName(String scriptFileName) {
		this.scriptFileName = scriptFileName;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
}
