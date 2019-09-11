package base.rest.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import base.annotation.RestController;
import base.rest.utils.HtmlHelper;
import base.servlet.ServletHelper;
import html.base.form.UploadForm;

/**
 * FileUploadApi handles ...
 * 
 * @author (original): <a href="mailto:yshi@infor.com">yshi</a>
 * @author (most recent): $LastChangedBy$
 * @version: $LastChangedRevision$
 * @created: Sep 11, 2019
 * @modified: $LastChangedDate$
 */
@RestController
public class FileUploadApi {
	
    private static final String UPLOAD_DIRECTORY = "upload";
	
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
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
		ServletContext context = ServletHelper.getContext();
		
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: form must include \"enctype=multipart/form-data\"");
            writer.flush();
            return;
        }
		
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
		
     // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                            "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        // 跳转到 message.jsp
//        try {
//			request.getServletContext().getRequestDispatcher("/message.jsp").forward(
//			        request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
