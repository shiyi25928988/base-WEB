package base.rest.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import base.annotation.RestAPI;
import base.rest.utils.HtmlHelper;
import base.servlet.ServletHelper;
import html.base.form.UploadForm;
import lombok.extern.slf4j.Slf4j;


/**
 * @author yshi
 *
 */
@Slf4j
@RestAPI
public class FileUploadApi {

	private static final String UPLOAD_DIRECTORY = "upload";

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@GET
	@Path(value = "/uploadform")
	public void getUploadForm() {
		HtmlHelper.sendHtmlPage(new UploadForm(ServletHelper.getRequest().getContextPath()).renderForm().render());
	}

	@POST
	@Path(value = "/upload")
	public void uploadFile() throws IOException {

		HttpServletRequest request = ServletHelper.getRequest();
		HttpServletResponse response = ServletHelper.getResponse();

		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer = response.getWriter();
			writer.println("Error: form must include \"enctype=multipart/form-data\"");
			writer.flush();
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setFileSizeMax(MAX_FILE_SIZE);

		upload.setSizeMax(MAX_REQUEST_SIZE);

		// set encoding format
		upload.setHeaderEncoding("UTF-8");

		// upload folder path
		String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
		log.info("uploadPath: " + uploadPath);

		// create folder if doesn't exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// extract file
			List<FileItem> formItems = upload.parseRequest(request);
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						log.info("filePath: " + filePath);

						// write into disk
						item.write(storeFile);
						request.setAttribute("message", "Succeed Upload!");
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "error: " + ex.getMessage());
		}

//        try {
//			request.getServletContext().getRequestDispatcher("/message.jsp").forward(
//			        request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
