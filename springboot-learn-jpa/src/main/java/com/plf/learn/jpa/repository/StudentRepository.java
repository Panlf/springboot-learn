package com.plf.learn.jpa.repository;

import java.util.List;
import java.util.Map;

import com.plf.learn.jpa.bean.Student;
import com.plf.learn.jpa.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student,Integer>,JpaSpecificationExecutor<Student>{
	@Query(value="select new com.learn.springbootjpa.dto.StudentDto(st.id,st.name,st.age,st.schoolId,sc.schoolName) from Student st left join School sc on sc.id=st.schoolId")
	public List<StudentDto> findStudentDto();
	
	
	@Query(value="select st.id,st.name,st.age,st.school_id,sc.school_name from student st left join school sc on sc.id=st.school_id",nativeQuery=true)
	public List<Map<String, Object>> findStudentDtoMap();
	
}
