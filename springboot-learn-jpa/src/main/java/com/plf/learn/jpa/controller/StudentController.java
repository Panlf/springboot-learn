package com.plf.learn.jpa.controller;

import java.util.List;
import java.util.Map;

import com.plf.learn.jpa.bean.Student;
import com.plf.learn.jpa.dto.StudentDto;
import com.plf.learn.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/findByName")
	public List<Student> findByName(@RequestParam(required=false) String name){
		return studentService.findStudentByName(name);
	}
	
	@RequestMapping("/findStudentDto")
	public List<StudentDto> findStudentDto(){
		return studentService.findStudentDto();
	}
	
	@RequestMapping("/findStudentDtoMap")
	public List<Map<String, Object>> findStudentDtoMap(){
		return studentService.findStudentDtoMap();
	}
}
