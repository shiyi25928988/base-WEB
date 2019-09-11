package html.base.form;

import static j2html.TagCreator.br;
import static j2html.TagCreator.form;
import static j2html.TagCreator.input;
import static j2html.TagCreator.tag;

import j2html.tags.DomContent;

public class UploadForm {
	
	private final String contexPath;
	
	public UploadForm(final String contexPath) {
		this.contexPath = contexPath;
	}

	
	public DomContent renderForm() {
		
		DomContent form = form(
						br(),
						tag("File to upload: "),
						input().withType("file").withName("upload"),
						br(),
						input().withType("text").withName("note"),
						br(),
						input().withType("submit").withValue("press")
						
						).withMethod("POST").attr("enctype", "multipart/form-data").withAction(".." + contexPath + "/upload");
		
		
		return form;
		
	}
}
