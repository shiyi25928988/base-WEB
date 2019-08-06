package html.base.element;

/**
 * @author yshi
 *
 */
public interface HtmlElement<T extends HtmlElement<?>> {

	T setClasses(String...classes);
	
	T setName(String name);
	
	T setId(String id);
	
	T addAttribute(String name, String value);
	
	T hide(boolean condition);
	
	String getId();
	
	String getName();
}
