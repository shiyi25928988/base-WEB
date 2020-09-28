package core.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import core.annotation.Controller;
import core.http.result.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class HelloWord {

	@GET
	@Path(value = "/hello")
	public JSON<String> hello() {
		return new JSON<String>("Hello world");
	}
	
	@GET
	@Path(value = "/hello2")
	public JSON<TestPojo> hello2() {
		return new JSON<TestPojo>(new TestPojo("SHIYI","30"));
	}
	
	@Data
	@AllArgsConstructor
	class TestPojo{
		String name;
		String age;
	}
}
