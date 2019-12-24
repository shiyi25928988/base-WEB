package selenium.base.command;

import selenium.base.script.ICommand;

/**
 * @author yshi
 *
 */

public class CommandFactory {

	public ICommand buildCommand(CommandType type) {
		
		switch (type){
		case CLICK:
		case CTRL_A:
			break;
		case CTRL_C:
			break;
		case CTRL_V:
			break;
		case F1:
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
