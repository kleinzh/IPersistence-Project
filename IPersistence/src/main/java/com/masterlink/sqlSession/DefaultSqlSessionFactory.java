package com.masterlink.sqlSession;

import com.masterlink.pojo.Configuration;

/**
 * @author Klein
 * @Classname DefaultSqlSessionFactory
 * @Description TODO
 * @Date 2020/12/25 16:50
 * @Created by Klein
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration=configuration;
    }

    @Override
    public SqlSession sqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
