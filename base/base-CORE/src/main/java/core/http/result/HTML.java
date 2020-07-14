package core.http.result;

import core.http.MimeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shiyi
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HTML implements ReturnType<String>{

	private String html;
	
	private final MimeType mimeType = MimeType.TEXT_HTML;
	private final String suffix = ".html";
	
	@Override
	public String getData() {
		return html;
	}
}
