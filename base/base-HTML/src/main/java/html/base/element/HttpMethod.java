package html.base.element;

public enum HttpMethod {
	GET("get"),
	PUT("put"),
	POST("post"),
	DELETE("delete");
	
	public String methodName;
	
	HttpMethod(String methodName) {
		this.methodName = methodName;
	}
	
}
