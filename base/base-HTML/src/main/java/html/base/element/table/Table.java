package html.base.element.table;

import html.base.element.HtmlElement;
import j2html.tags.ContainerTag;

public class Table extends ContainerTag implements HtmlElement<Table>{

	public Table() {
		super("table");
		// TODO Auto-generated constructor stub
	}
	
	public Table addColumn(String columnTitle) {
		
		return this;
	}

	@Override
	public Table setClasses(String... classes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table setName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table setId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table addAttribute(String name, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table hide(boolean condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class Tr{}
	private class Th{}
	private class Td{}

}
