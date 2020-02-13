package com.plf.learn.document.utils;

import java.io.Closeable;
import java.io.IOException;
/**
 * @author Panlf
 * @date 2020/2/13
 */
public class CloseUtils {
    public static void close(Closeable...closeables) {
        if(closeables==null) {
            return;
        }
        for(Closeable closeable:closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
