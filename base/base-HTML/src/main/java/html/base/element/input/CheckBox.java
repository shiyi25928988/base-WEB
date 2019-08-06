package html.base.element.input;

/**
 * @author yshi
 *
 */
public class CheckBox extends Input{
	
	public CheckBox() {
		super();
		this.setInputType(InputType.CheckBox);
	}
	
	public CheckBox(String value) {
		super();
		this.setInputType(InputType.CheckBox);
		this.withValue(value);
	}
	
	public CheckBox setValue(String value) {
		this.withValue(value);
		return this;
	}

}
