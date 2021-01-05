package com.masterlink.pojo;

import lombok.Data;

/**
 * @Classname MappedStatement
 * @Description TODO
 * @Date 2020/12/23 19:58
 * @Created by Klein
 */
@Data
public class MappedStatement {

    /**
     * id标识
     */
    private String id;

    /**
     * 返回值类型
     */
    private String resultType;

    /**
     * 参数值类型
     */
    private String parameterType;

    /**
     * sql语句
     */
    private String sql;

}
