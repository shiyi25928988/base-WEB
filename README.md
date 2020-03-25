# 写给自己用的微服务应用框架
## 基于Guice封装的
### 没有文档，没打算给别人用 
#### 启动非常快

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
