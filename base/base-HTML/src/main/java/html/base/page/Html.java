package html.base.page;

import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.link;
import static j2html.TagCreator.meta;
import static j2html.TagCreator.style;

import j2html.attributes.Attribute;
import j2html.tags.DomContent;

/**
 * @author yshi
 *
 */
public abstract class Html {
	
	protected static String contexPath;
	
	public abstract DomContent renderBody();

	public DomContent renderHead() {
		return head(
				meta().attr(new Attribute("charset", "UTF-8")),
				meta().attr(new Attribute("http-equiv", "X-UA-Compatible"))
					  .attr(new Attribute("content", "IE=edge")),
				meta().attr(new Attribute("name", "viewport"))
					  .attr(new Attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no")),
				link().withRel("stylesheet")
					  .withHref(".."+ contexPath +"/css/bootstrap.min.css")
					  .attr(new Attribute("integrity", "sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"))
					  .attr(new Attribute("integrity", "SHA-256-nbLgBB/0rf6PCm0cOwg92x3xACxy8WstRCJbFo98VNQ="))
					  .attr(new Attribute("crossorigin", "anonymous")),
				style("\r\n" + 
						"      .bd-placeholder-img {\r\n" + 
						"        font-size: 1.125rem;\r\n" + 
						"        text-anchor: middle;\r\n" + 
						"        -webkit-user-select: none;\r\n" + 
						"        -moz-user-select: none;\r\n" + 
						"        -ms-user-select: none;\r\n" + 
						"        user-select: none;\r\n" + 
						"      }\r\n" + 
						"\r\n" + 
						"      @media (min-width: 768px) {\r\n" + 
						"        .bd-placeholder-img-lg {\r\n" + 
						"          font-size: 3.5rem;\r\n" + 
						"        }\r\n" + 
						"      }\r\n" + 
						"    "),
				link().withRel("stylesheet")
					  .withHref(".."+ contexPath +"/css/signin.css")
			);
	}
	
	public String render() {
		return html(renderHead(), renderBody()).render();
	}
}
