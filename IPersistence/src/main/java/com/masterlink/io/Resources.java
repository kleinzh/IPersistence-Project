package com.masterlink.io;

import java.io.InputStream;

/**
 * @Classname Resources
 * @Description TODO
 * @Date 2020/12/23 19:41
 * @Created by Klein
 */
public class Resources {

    /**
     *  根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path) {
        InputStream inputStream=Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
