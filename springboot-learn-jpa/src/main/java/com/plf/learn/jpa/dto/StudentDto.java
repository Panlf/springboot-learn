package com.plf.learn.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private Integer schoolId;
	
	private String schoolName;
}
