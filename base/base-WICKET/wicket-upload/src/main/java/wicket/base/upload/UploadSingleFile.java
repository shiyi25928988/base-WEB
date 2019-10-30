/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package wicket.base.upload;

import java.io.File;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Bytes;

import wicket.base.bootstrap.layout.BootstrapBasePage;

public class UploadSingleFile extends BootstrapBasePage {
	private static final long serialVersionUID = 1L;
	private FileUploadField fileUploadField;

    public UploadSingleFile(final PageParameters parameters) {
    	
    	fileUploadField = new FileUploadField("fileUploadField");
    	
    	Form form = new Form("form"){
    		@Override
    		protected void onSubmit() {
    		  super.onSubmit();
    			 
    		  FileUpload fileUpload = fileUploadField.getFileUpload();
    			
    		    try {
    		    	File file = new File(System.getProperty("java.io.tmpdir") + "/" +
    						fileUpload.getClientFileName());
    				
    		    	fileUpload.writeTo(file);
    		    	info("Upload completed!");
		        } catch (Exception e) {
		        	e.printStackTrace();
		        	error("Upload failed!");
			 }
    		}
    	};	
	
		form.setMultiPart(true);
		//set a limit for uploaded file's size
		form.setMaxSize(Bytes.kilobytes(100));
		form.add(fileUploadField);
		add(new FeedbackPanel("feedbackPanel"));
		add(form);
    }
}
