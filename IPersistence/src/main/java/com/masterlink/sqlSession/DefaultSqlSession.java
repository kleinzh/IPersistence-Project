package com.masterlink.sqlSession;

import com.masterlink.pojo.Configuration;
import com.masterlink.pojo.MappedStatement;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Classname DefaultSqlSession
 * @Description TODO
 * @Date 2020/12/25 16:58
 * @Created by Klein
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {

        //将要去完成对SimpleExecutor里得query方法得调用
        SimpleExectuor simpleExectuor=new SimpleExectuor();
        MappedStatement mappedStatement=configuration.getMappedStatementMap().get(statementid);
        List<Object> result=simpleExectuor.query(configuration,mappedStatement,params);
        return  (List<E>)result;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if(objects.size()==1){
            return (T) objects.get(0);
        }else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }
}
