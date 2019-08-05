package base.mvc.controller;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import base.annotation.Controller;
import base.rest.HtmlHelper;
import base.rest.MimeType;
import base.servlet.ServletHelper;

/**
 * @author yshi
 *
 */
@Controller
public class ImageController {

	@GET
	@Path(value = "/image/pussy.png")
	public void getPussy() throws IOException {
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator +"image" + File.separator + "pussy.png";
		HtmlHelper.sendImage(filePath, MimeType.IMAGE_PNG);
	}
	

}
