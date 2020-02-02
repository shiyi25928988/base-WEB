package selenium.base.command;

public enum CommandType {

	EMPTY,
	
	OPEN,//open a url
	
	CLICK,// click on link or button
	INPUT,//input into a input element or text area
	CLEAR,// clear a input element or text area
	SELECT,//select on a radio or a dropdown element
	CHECK,// checkbox
	DROPDOWN,// dropdown list
}
