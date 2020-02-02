package selenium.base.command;

import selenium.base.script.Result;
import selenium.base.script.Script;

public interface ICommand {

	Result execute();
	
	Script getScript();

	int getLineNum();
}
