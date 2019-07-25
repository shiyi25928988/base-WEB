package html.base.element;

public interface Element {

	Element setClass(String className);
	
	Element setName(String name);
	
	Element setId(String id);
	
	Element setText(String text);
	
	Element setType(String type);
}
