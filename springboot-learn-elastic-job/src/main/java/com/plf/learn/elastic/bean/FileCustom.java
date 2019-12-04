package com.plf.learn.elastic.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCustom {

	private String id;
	
	private String name;
	
	private String type;
	
	private String path;
	
	private Boolean backUp=false;

}
