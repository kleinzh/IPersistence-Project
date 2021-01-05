package com.masterlink.sqlSession;

import java.util.List;

/**
 * @Classname SqlSession
 * @Description TODO
 * @Date 2020/12/25 16:57
 * @Created by Klein
 */
public interface SqlSession {

    /**
     * 查询所有
     * @param statementid
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E>  List<E> selectList(String statementid,Object... params) throws Exception;

    /**
     * 根据条件查询单个
     * @param statementid
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T selectOne(String statementid,Object... params) throws Exception;
}
