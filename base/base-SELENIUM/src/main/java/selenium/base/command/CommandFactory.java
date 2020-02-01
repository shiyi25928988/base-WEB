package selenium.base.command;

import selenium.base.command.impl.EmptyCommand;

/**
 * @author yshi
 *
 */

public class CommandFactory {

	public ICommand buildCommand(CommandType type) {

		switch (type) {
		case CLICK:

			break;
		case INPUT:
			break;
		case OPEN:
			break;
		default:
			break;
		}

		return new EmptyCommand();
	}
}
