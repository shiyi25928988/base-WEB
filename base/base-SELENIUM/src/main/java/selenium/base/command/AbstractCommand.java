package selenium.base.command;

import selenium.base.script.Script;

public abstract class AbstractCommand implements ICommand{

	private Script script;
	private int lineNum;
	
	public AbstractCommand(final Script script, final int lineNum) {
		this.script = script;
		this.lineNum = lineNum;
	}


	public Script getScript() {
		return script;
	}


	public int getLineNum() {
		return lineNum;
	}

}
