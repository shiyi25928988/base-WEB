package selenium.base.script;

import java.util.Queue;

/**
 * @author shiyi
 *
 */
public class Script {

	private String scriptFileName;
	
	private Script nextScript;
	
	private Queue<ScriptLineObject> content;
	
	public Script(String scriptFileName, Queue<ScriptLineObject> content, Script nextScript) {
		this(scriptFileName, content);
		this.nextScript = nextScript;
	}
	
	public Script(String scriptFileName, Queue<ScriptLineObject> content) {
		this.scriptFileName = scriptFileName;
		this.content = content;
	}

	public String getScriptFileName() {
		return scriptFileName;
	}

	public Script getNextScript() {
		return nextScript;
	}

	public Queue<ScriptLineObject> getContent() {
		return content;
	}
	
}
