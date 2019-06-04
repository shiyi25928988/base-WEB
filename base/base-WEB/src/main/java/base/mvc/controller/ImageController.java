package base.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import base.annotation.Controller;
import base.rest.HtmlHelper;
import base.rest.MimeType;
import base.servlet.ServletHelper;

@Controller
public class ImageController {

	@GET
	@Path(value = "/image/pussy.png")
	public void getPussy() throws IOException {
		String filePath = ServletHelper.getRealPath() + "WEB-INF" + File.separator +"image" + File.separator + "pussy.png";
		HtmlHelper.sendImage(filePath, MimeType.IMAGE_PNG);
	}
	

}
