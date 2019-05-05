package com.plf.learn.jpa.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.plf.learn.jpa.bean.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Panlf
 * @date 2019/5/5
 */
@SpringBootTest//系统会自动加载Spring Boot容器
@RunWith(SpringRunner.class)
public class ControllerTest {

    private MockMvc mockMvc;//模拟http请求

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;


    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getStudent(){
        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/student/findByName?name=Aster")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print()).andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
		/*
		 * mockMvc.perform执行一个请求
		 *
		 * MockMvcRequestBuilders.get 构造一个请求
		 *
		 * ResultActions.andDo 添加一个结果处理器，表示对结果做点什么
		 *
		 * */
    }

    @Test
    public void addStudent(){
        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/student/add")
                    .param("name", "Apple")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addStudentByJson(){
        try {
            Student student = new Student();
            student.setName("Banana");
            String requestBody = mapper.writeValueAsString(student);
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/student/addJson")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(requestBody)
                    //.param("name", "Banana")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
