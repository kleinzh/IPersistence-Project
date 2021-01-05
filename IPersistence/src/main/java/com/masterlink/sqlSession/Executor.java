package com.masterlink.sqlSession;

import com.masterlink.pojo.Configuration;
import com.masterlink.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Classname Executor
 * @Description TODO
 * @Date 2020/12/25 17:26
 * @Created by Klein
 */
public interface Executor {

    /**
     *  封装调用jdbc的执行语句
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     */
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException;
}
