package html.base.element.input;

public enum InputType {
	Button("button"),
	CheckBox("checkbox"),
	File("file"),
	Hidden("hidden"),
	Image("image"),
	Password("password"),
	Radio("radio"),
	Reset("reset"),
	Submit("submit"),
	Text("text");
	
	public final String type;
	InputType(String type){
		this.type = type;
	}
	
}
