package com.masterlink.test;

import com.masterlink.io.Resources;
import com.masterlink.pojo.User;
import com.masterlink.sqlSession.SqlSessionFactory;
import com.masterlink.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import javax.jws.soap.SOAPBinding;
import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Classname IPersistenceTest
 * @Description TODO
 * @Date 2020/12/23 19:53
 * @Created by Klein
 */
public class IPersistenceTest {

    public void test() throws Exception {

        InputStream inputStream=  Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);

        User user= new User();
        user.setId(1);
        user.setUsername("Klein-Zhang");

        User result=sqlSessionFactory.sqlSession().selectOne("user.selectOne",user);


    }
}
