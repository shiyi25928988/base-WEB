package selenium.base.script;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;
import selenium.base.command.CommandFactory;
import selenium.base.command.ICommand;
import selenium.base.exception.ScriptParseException;

/**
 * @author shiyi
 *
 */
@Slf4j
public class DefaultScriptParser implements IScriptParser{

	/**
	 *
	 */
	@Override
	public Queue<ICommand> parse(Script script) throws ScriptParseException {
		log.info("DefaultScriptParser ...");
		
		Queue<ICommand> queue = new LinkedBlockingQueue<>();
		
		//parsing......
		//related to CommandFactory.class
		script.getContent().forEach(scriptLineObject ->{
			
			int lineNum = scriptLineObject.getLineNum();
			String content = scriptLineObject.getLineContent();
			
			queue.add(CommandFactory.buildCommand(content, lineNum));
			
		});
		
		return queue;
	}
	
}
