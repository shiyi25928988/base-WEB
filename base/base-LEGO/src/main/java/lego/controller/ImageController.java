package lego.controller;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lego.annotation.RestAPI;
import lego.rest.utils.MimeType;
import lego.rest.utils.RestHelper;
import lego.servlet.ServletHelper;

/**
 * @author yshi
 *
 */
@RestAPI
public class ImageController {

	@GET
	@Path(value = "/image/pussy.png")
	public void getPussy() throws IOException {
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator +"image" + File.separator + "pussy.png";
		RestHelper.sendImage(filePath, MimeType.IMAGE_PNG);
	}
	
	@GET
	@Path(value = "/logo.png")
	public void getLogo() throws IOException {
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator +"image" + File.separator + "logo.png";
		RestHelper.sendImage(filePath, MimeType.IMAGE_PNG);
	}
	

}
