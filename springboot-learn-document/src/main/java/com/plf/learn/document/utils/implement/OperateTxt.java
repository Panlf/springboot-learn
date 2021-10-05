package com.plf.learn.document.utils.implement;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.plf.learn.document.utils.CloseUtils;
import com.plf.learn.document.utils.interfaces.OperateDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *  读写Txt文档
 * @author Panlf
 * @date 2020/2/13
 */
@Component(value = "txt")
public class OperateTxt implements OperateDocument {

    @Override
    public String readDocument(String path) {
        System.out.println("正在使用TXT处理器在处理");
        if(StringUtils.isEmpty(path)){
            return "";
        }

        String content = "";

        InputStreamReader inputStreamReader = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedReader bufferedReader = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(path);

            InputStream inputStream = classPathResource.getInputStream();

            bufferedInputStream = new BufferedInputStream(inputStream);

            inputStreamReader = new InputStreamReader(bufferedInputStream, "UTF-8");

            bufferedReader = new BufferedReader(inputStreamReader, 5 * 1024 * 1024);

            String text = null;

            StringBuffer stringBuffer = new StringBuffer();
            // 逐行读取
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }

            content = stringBuffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(inputStreamReader, bufferedInputStream, bufferedReader);
        }

        return content;
    }

    @Override
    public boolean writeDocument(String path, String content) {
        boolean flag = false;
        FileOutputStream fileOutputStream = null;
        File file = new File(path);
        try {
            // 判断文件是否存在，如果不存在就新建一个txt
            if (file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseUtils.close(fileOutputStream);
        }
        return flag;
    }

}