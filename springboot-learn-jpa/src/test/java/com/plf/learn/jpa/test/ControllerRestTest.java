package com.plf.learn.jpa.test;

import com.plf.learn.jpa.bean.Student;
import com.plf.learn.jpa.dto.StudentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Panlf
 * @date 2019/5/5
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ControllerRestTest {

    @Value("http://localhost:${local.server.port}/jpa/student")
    private String baseUrl;

    //private ObjectMapper mapper = new ObjectMapper();

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getStudent(){
        List<StudentDto> list=restTemplate.getForObject(baseUrl+"/findStudentDto", List.class);
        System.out.println(list);
    }

    @Test
    public void addStudent(){
        //当直接传递参数需要用map
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "Aster");
        Student studnet=restTemplate.postForObject(baseUrl+"/add", paramMap, Student.class);
        System.out.println(studnet);
    }

    @Test
    public void addStudentByJson(){
        try{
            Student s = new Student();
            s.setName("Banana");
			/*HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		    HttpEntity<String> formEntity = new HttpEntity<String>(
		    mapper.writeValueAsString(p), headers);*/
            Student student=restTemplate.postForObject(baseUrl+"/addJson", s, Student.class);
            System.out.println(student);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

