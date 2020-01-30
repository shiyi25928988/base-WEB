package selenium.base.script;

import java.util.Queue;

import selenium.base.exception.ScriptParseException;

public interface IScriptParser {

	Queue<ICommand> parse(IScript script) throws ScriptParseException;
}
