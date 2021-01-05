package com.masterlink.config;

import com.masterlink.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname BoundSql
 * @Description TODO
 * @Date 2020/12/30 20:07
 * @Created by Klein
 */
public class BoundSql {

    private String sqlText; //解析过后的sql

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String parseSql, List<ParameterMapping> parameterMappings) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
