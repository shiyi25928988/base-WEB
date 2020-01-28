package selenium.base.guice;

import org.apache.commons.cli.ParseException;

import lombok.extern.slf4j.Slf4j;
import selenium.base.cli.CliArgsProcessor;

@Slf4j
public class SeleniumExecuteServiceImpl implements SeleniumExecuteService{

	@Override
	public void run(String... strings) {
		// TODO Auto-generated method stub
		
		log.info("run..");
		
		CliArgsProcessor processor = new CliArgsProcessor();
		try {
			processor.process(strings);
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		
	}

}
