package com.plf.learn.elastic.service;

import com.plf.learn.elastic.bean.FileCustom;
import com.plf.learn.elastic.mapper.FileCustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Panlf
 * @date 2019/12/2
 */
@Service
public class FileCustomService {

    @Autowired
    private FileCustomMapper fileCustomMapper;

    public List<FileCustom> findFileCustomList(String type,int limitnum){
        return fileCustomMapper.findFileCustomByTypeLimit(type,limitnum);
    }

    public void updateFileCustom(String id){
        fileCustomMapper.updateBackUp(id);
    }
}
