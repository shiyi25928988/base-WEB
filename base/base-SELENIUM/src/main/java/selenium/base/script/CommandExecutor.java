package selenium.base.script;

import java.util.Queue;

import selenium.base.command.ICommand;

/**
 * @author shiyi
 *
 */
public class CommandExecutor implements Runnable{

	private final Queue<ICommand> commands;
	
	public CommandExecutor(Queue<ICommand> commands) {
		this.commands = commands;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!this.commands.isEmpty()) {
			this.commands.poll().execute();
		}
	}

}
