package com.plf.learn.elastic.mapper;

import com.plf.learn.elastic.bean.FileCustom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Panlf
 * @date 2019/12/2
 */
@Mapper
public interface FileCustomMapper {

    @Select("select id,name,type,path,backup from filecustom where type=#{type} and backup=0 limit #{limitnum}")
    public List<FileCustom> findFileCustomByTypeLimit(@Param("type") String type,@Param("limitnum")  int limitnum);

    @Update("update filecustom set backup=1 where id=#{id}")
    public void updateBackUp(@Param("id") String id);

}