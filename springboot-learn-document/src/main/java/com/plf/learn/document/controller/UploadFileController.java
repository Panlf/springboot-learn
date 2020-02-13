package com.plf.learn.document.controller;

import com.plf.learn.document.service.AnalysisFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("upload")
public class UploadFileController {

	@Autowired
	private AnalysisFileService analysisFileService;
	
	@GetMapping("save")
	public String upload() {
		analysisFileService.insertdb();
		return "正在解析....";
	}
	
}
