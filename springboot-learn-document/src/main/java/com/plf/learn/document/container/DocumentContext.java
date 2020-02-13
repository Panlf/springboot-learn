package com.plf.learn.document.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.plf.learn.document.utils.interfaces.OperateDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DocumentContext {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentContext.class);
	
	@Autowired
    private final Map<String, OperateDocument> documentMap = new ConcurrentHashMap<>();
 
	/**
	 * 根据key名为beanName,value为实例类装入到documentMap
	 * @param documentMap
	 */
    public DocumentContext(Map<String, OperateDocument> documentMap) {
        this.documentMap.clear();
        documentMap.forEach((k, v)-> this.documentMap.put(k, v));
        logger.info("策略模式装入documentMap:{}",documentMap);
    }

	public String readDocument(String beanName,String path){
		return this.getResource(beanName).readDocument(path);
	}
	
	public boolean writeDocument(String beanName,String path,String content){
		return this.getResource(beanName).writeDocument(path, content);
	}
	
	public OperateDocument getResource(String beanName) {
		return documentMap.get(beanName);
	}
}
