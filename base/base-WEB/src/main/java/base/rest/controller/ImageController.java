package base.rest.controller;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import base.annotation.RestController;
import base.rest.utils.HtmlHelper;
import base.rest.utils.MimeType;
import base.servlet.ServletHelper;

/**
 * @author yshi
 *
 */
@RestController
public class ImageController {

	@GET
	@Path(value = "/image/pussy.png")
	public void getPussy() throws IOException {
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator +"image" + File.separator + "pussy.png";
		HtmlHelper.sendImage(filePath, MimeType.IMAGE_PNG);
	}
	
	@GET
	@Path(value = "/logo.png")
	public void getLogo() throws IOException {
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator +"image" + File.separator + "logo.png";
		HtmlHelper.sendImage(filePath, MimeType.IMAGE_PNG);
	}
	

}
