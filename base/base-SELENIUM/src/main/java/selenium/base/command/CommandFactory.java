package selenium.base.command;

import selenium.base.command.impl.EmptyCommand;

/**
 * @author yshi
 *
 */

public class CommandFactory {

	/**
	 * @param scriptLine
	 * @param lineNum
	 * @return
	 */
	public static ICommand buildCommand(String scriptLine, int lineNum) {
		CommandType type = getCommandType(scriptLine);
		
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
	
	/**
	 * @param scriptLine
	 * @return
	 */
	private static CommandType getCommandType(String scriptLine) {
		
		if (scriptLine.toLowerCase().startsWith("open")){
			return CommandType.OPEN;
		}
		
		if (scriptLine.toLowerCase().startsWith("click")){
			return CommandType.CLICK;
		}
		
		
		return CommandType.EMPTY;
	}
}
