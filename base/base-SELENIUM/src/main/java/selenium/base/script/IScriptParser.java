package selenium.base.script;

import java.util.Queue;

import selenium.base.command.ICommand;
import selenium.base.exception.ScriptParseException;

public interface IScriptParser {

	Queue<ICommand> parse(Script script) throws ScriptParseException;
}
