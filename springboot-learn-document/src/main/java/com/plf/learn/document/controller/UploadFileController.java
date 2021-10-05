package com.plf.learn.document.controller;

import com.plf.learn.document.container.DocumentContext;
import com.plf.learn.document.service.AnalysisFileService;
import com.plf.learn.document.utils.interfaces.OperateDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("upload")
public class UploadFileController {

	@Autowired
	private DocumentContext documentContext;

	@Autowired
	private AnalysisFileService analysisFileService;
	
	@GetMapping("save")
	public String upload() {
		analysisFileService.insertdb();
		return "正在解析....";
	}

	@GetMapping("edit")
	public String editDocument(String dealKey){
		OperateDocument operateDocument  = documentContext.getResource(dealKey);
		return operateDocument.readDocument(null);
	}
	
}
