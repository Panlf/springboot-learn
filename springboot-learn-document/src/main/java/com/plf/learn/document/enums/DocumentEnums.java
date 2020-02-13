package com.plf.learn.document.enums;

/**
 * @author Panlf
 * @date 2020/2/13
 */
public enum DocumentEnums {
	TXT("txt"),WORD_DOC("word_doc"),WORD_DOCX("word_docx");
	
	private String name;
	
	DocumentEnums(){}
	
	DocumentEnums(String name){
		this.name= name;
	}
	
	public String getName(){
		return name;
	}
}
