package html.base.element.input;

import html.base.element.button.ButtonType;

/**
 * @author yshi
 *
 */
public class Button extends Input{

	public Button() {
		super();
		this.setInputType(InputType.Button);
	}
	
	public Button setType(ButtonType type) {
		switch(type) {
		case submit:
			this.withType("submit");
			break;
		case button:
			this.withType("button");
			break;
		case reset:
			this.withType("reset");
			break;
		}
		return this;
	}
	
	public Button setButtonText(String text) {
		this.setText(text);
		return this;
	}
	
}
