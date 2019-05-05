package com.plf.learn.jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.plf.learn.jpa.bean.Student;
import com.plf.learn.jpa.dto.StudentDto;
import com.plf.learn.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * 动态参数查询
	 * @param name
	 * @return
	 */
	public List<Student> findStudentByName(String name){
		Specification<Student> specification = new Specification<Student>() {

			private static final long serialVersionUID = 6520751345774664104L;

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();
				if(name!=null && name.trim().length()>0){
					list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return criteriaBuilder.and(list.toArray(p));
			}
		};
		
		return studentRepository.findAll(specification);
	}
	
	/**
	 * 使用原生SQL语句
	 * @return
	 */
	public List<StudentDto> findStudentDto(){
		return studentRepository.findStudentDto();
	}
	
	/**
	 * 使用原生SQL语句
	 * @return
	 */
	public List<Map<String, Object>> findStudentDtoMap(){
		return studentRepository.findStudentDtoMap();
	}
}
