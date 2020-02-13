package com.plf.learn.document.utils.interfaces;

/**
 * @author Panlf
 * @date 2020/2/13
 */
public interface OperateDocument {

    /**
     *  读文档
     * @param path
     * @return
     */
    public String readDocument(String path);

    /**
     * 写文档
     * @param path
     * @param content
     * @return
     */
    public boolean writeDocument(String path,String content);
}
