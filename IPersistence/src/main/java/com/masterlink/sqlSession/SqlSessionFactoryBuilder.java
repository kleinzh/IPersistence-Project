package com.masterlink.sqlSession;

import com.masterlink.config.XmlConfigBuilder;
import com.masterlink.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Classname SqlSessionFactoryBuilder
 * @Description TODO
 * @Date 2020/12/23 20:12
 * @Created by Klein
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {

        //第一: 使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XmlConfigBuilder xmlConfigBuilder=new XmlConfigBuilder();
        Configuration configuration=xmlConfigBuilder.parseConfig(inputStream);

        //第二: 创建sqlSessionFactory对象
        SqlSessionFactory sessionFactory=new DefaultSqlSessionFactory(configuration);

        return sessionFactory;

    }
}
