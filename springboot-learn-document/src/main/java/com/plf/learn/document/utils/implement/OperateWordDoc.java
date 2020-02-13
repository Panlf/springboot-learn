package com.plf.learn.document.utils.implement;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.plf.learn.document.utils.CloseUtils;
import com.plf.learn.document.utils.interfaces.OperateDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


/**
 *  读写Word Doc后缀的文档
 * @author Panlf
 * @date 2020/2/13
 */
@Component(value = "word_doc")
public class OperateWordDoc implements OperateDocument {

	/**
	 * 读取ClassPath下的Doc文档
	 */
	@Override
	public String readDocument(String path) {
		String text = "";
		InputStream inputStream=null;
		WordExtractor wordExtractor=null;
		try {
			ClassPathResource classPathResource = new ClassPathResource(path);

			inputStream = classPathResource.getInputStream();
			
			wordExtractor = new WordExtractor(inputStream);

			text = wordExtractor.getText();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtils.close(inputStream,wordExtractor);
		}
		
		return text;
	}

	/**
	 * 写ClassPath下的Doc文档
	 */
	@Override
	public boolean writeDocument(String path, String content) {
		InputStream inputStream = null;
		
		HWPFDocument doc = null;
		
		FileOutputStream fileOutputStream = null;
		
		boolean flag = false;
		try {
			ClassPathResource classPathResource = new ClassPathResource("templates/word/template.doc");

			inputStream = classPathResource.getInputStream();
			
			doc = new HWPFDocument(inputStream);
			
			Range range = doc.getRange();
			
			String allTitle = path.substring(path.lastIndexOf("\\")+1);
			String title=allTitle.substring(0, allTitle.lastIndexOf("."));
			
			range.replaceText("${title}", title);
			
			range.replaceText("${content}", content);
			
			fileOutputStream = new FileOutputStream(path);
			
			// 把doc输出到输出流中
			doc.write(fileOutputStream);
			
			flag = true;

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtils.close(inputStream,doc,fileOutputStream);
		}
		
		return flag;
	}

}
