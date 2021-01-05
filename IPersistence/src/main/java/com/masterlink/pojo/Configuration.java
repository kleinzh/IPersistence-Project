package com.masterlink.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Configuration
 * @Description TODO
 * @Date 2020/12/23 20:06
 * @Created by Klein
 */
@Data
public class Configuration {

    private DataSource dataSouce;

    /**
     * key: statementid
     * value:封装好的mappedStatement对象
     */
    private Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

}
