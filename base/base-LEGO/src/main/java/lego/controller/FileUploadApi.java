package lego.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import html.base.form.UploadForm;
import lego.annotation.Controller;
import lego.rest.result.HTML;
import lego.rest.result.Result;
import lego.servlet.ServletHelper;
import lombok.extern.slf4j.Slf4j;


/**
 * @author yshi
 *
 */
@Slf4j
@Controller
public class FileUploadApi {

	private static final String UPLOAD_DIRECTORY = "upload";

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@GET
	@Path(value = "/uploadform")
	public HTML getUploadForm() {
		return new HTML(new UploadForm(ServletHelper.getRequest().getContextPath()).renderForm().render());
	}

	@POST
	@Path(value = "/upload")
	public Result uploadFile() throws IOException {

		HttpServletRequest request = ServletHelper.getRequest();

		if (!ServletFileUpload.isMultipartContent(request)) {
			return Result.Builder.failed("Error: form must include \"enctype=multipart/form-data\"");
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
		return Result.Builder.success("Succeed Upload!");
	}

}
