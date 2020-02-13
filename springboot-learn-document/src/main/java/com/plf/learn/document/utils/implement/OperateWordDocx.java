package com.plf.learn.document.utils.implement;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.plf.learn.document.utils.CloseUtils;
import com.plf.learn.document.utils.interfaces.OperateDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


/**
 *  读写Word Docx后缀的文档
 * @author Panlf
 * @date 2020/2/13
 */
@Component(value = "word_docx")
public class OperateWordDocx implements OperateDocument {

	@Override
	public String readDocument(String path) {
		InputStream inputStream=null;
		String text = "";
		XWPFDocument doc = null;
		XWPFWordExtractor extractor = null;
		try {
			ClassPathResource classPathResource = new ClassPathResource(path);

			inputStream = classPathResource.getInputStream();
			
			doc = new XWPFDocument(inputStream);
			
			extractor = new XWPFWordExtractor(doc);
			
			text = extractor.getText();

			extractor.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtils.close(inputStream,doc,extractor);
		}
		
		return text;
	}

	@Override
	public boolean writeDocument(String path, String content) {
		boolean flag = false;
		XWPFDocument doc = null;
		FileOutputStream fileOutputStream = null;
		try {
			// 新建一个文档
			doc = new XWPFDocument();
			// 创建一个段落
			XWPFParagraph paraTitle = doc.createParagraph();

			paraTitle.setAlignment(ParagraphAlignment.CENTER);
			// 一个XWPFRun代表具有相同属性的一个区域。
			XWPFRun run = paraTitle.createRun();
            // 加粗
			run.setBold(true);
            //字体
			run.setFontFamily("宋体");
			run.setFontSize(28);
			String allTitle = path.substring(path.lastIndexOf("\\")+1);
			String title=allTitle.substring(0, allTitle.lastIndexOf("."));
			run.setText(title);
			
			XWPFParagraph paraContent = doc.createParagraph();
			
			//设置首行缩进
			//1厘米=567
			paraContent.setAlignment(ParagraphAlignment.BOTH);
			paraContent.setIndentationFirstLine(567);	
			
			run = paraContent.createRun();
			run.setText(content);
			fileOutputStream = new FileOutputStream(path);
			// 把doc输出到输出流
			doc.write(fileOutputStream);
			
			flag=true;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtils.close(doc,fileOutputStream);
		}
		return flag;
	}

}
