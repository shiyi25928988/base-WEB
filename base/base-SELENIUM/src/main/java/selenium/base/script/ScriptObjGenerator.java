package selenium.base.script;

import java.io.FileNotFoundException;

import selenium.base.utils.IOUtils;

public final class ScriptObjGenerator {

	public static Script gen(String scriptFilePath) throws FileNotFoundException{
		Script script = new Script(scriptFilePath, IOUtils.readLines(scriptFilePath));
		return script;
	}
}
