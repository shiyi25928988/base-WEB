package html.base.element.h;

/**
 * @author yshi
 *
 */
public class H3 extends H{

	/**
	 * 
	 */
	public H3() {
		super("h3");
	}
	
	private String id;
	private String name;
	
	@Override
	public H setName(String name) {
		this.withName(name);
		this.name = name;
		return this;
	}

	@Override
	public H setId(String id) {
		this.withId(id);
		return this;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
