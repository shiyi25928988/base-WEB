package base.crawler.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Tag;

/**
 * @author shiyi
 *
 */
public class HtmlNodeFilter implements NodeFilter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	boolean result = false;
	List<String> tagName = new ArrayList<>();
	
	public HtmlNodeFilter(String...strings) {
		tagName.addAll(Arrays.asList(strings));
	}

	@Override
	public boolean accept(Node node) {
		
		if (node instanceof Tag == false) return false;
		
		tagName.forEach(name ->{
			String tagName =((Tag)node).getTagName();
			
			if (tagName.equalsIgnoreCase(name)){
				result = true;
				return;
			}
		});
		
		return result;
	}

}
