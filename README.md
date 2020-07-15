

启动方式和SpringBoot类似，但要带上自己的Module对象
```java

public class Main {
	public static void main(String... strings) {
		try {
			ServiceBooter.startOn(Main.class, new CustomModule());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
```


HTTP接口代码示例
```java
@Controller
public class UserController {

	@Inject
	UserService userService;

	@GET
	@Path(value = "/test/user")
	public JSON<User> getUserByID(@PathParam(value = "USER_ID") String userId) {
		User user = userService.getUser(userId);
		return new JSON<User>(user);
	}
	
	@POST
	@Path(value = "/test/user/create")
	public Result create(@RequestBody User user) {
		// TODO
		userService.insert(user);
		return Result.Builder.success("create success!!");
	}

}
```
