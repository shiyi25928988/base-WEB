package selenium.base.script;

import java.util.Queue;

import lombok.extern.slf4j.Slf4j;
import selenium.base.command.ICommand;
import selenium.base.exception.ScriptParseException;

/**
 * @author shiyi
 *
 */
@Slf4j
public class DefaultScriptParser implements IScriptParser{

	@Override
	public Queue<ICommand> parse(Script script) throws ScriptParseException {
		log.info("DefaultScriptParser ...");
		
		return null;
	}

}
