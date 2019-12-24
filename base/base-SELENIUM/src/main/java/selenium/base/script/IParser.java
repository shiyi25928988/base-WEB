package selenium.base.script;

import selenium.base.exception.ScriptParseException;

public interface IParser {

	ICommand parse(IScript script) throws ScriptParseException;
}
