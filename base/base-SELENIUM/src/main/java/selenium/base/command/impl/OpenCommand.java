package selenium.base.command.impl;

import lombok.extern.slf4j.Slf4j;
import selenium.base.command.Command;
import selenium.base.script.Result;
import selenium.base.script.Script;

/**
 * @author shiyi
 *
 */
@Slf4j
public class OpenCommand extends Command{

	public OpenCommand(Script script, int lineNum) {
		super(script, lineNum);
	}

	@Override
	public Result execute() {
		log.info("OpenCommand");
		return null;
	}

}
