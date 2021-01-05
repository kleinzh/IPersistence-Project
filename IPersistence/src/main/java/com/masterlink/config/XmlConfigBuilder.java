package com.masterlink.config;

import com.masterlink.io.Resources;
import com.masterlink.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Classname XmlConfigBuilder
 * @Description TODO
 * @Date 2020/12/25 10:26
 * @Created by Klein
 */
public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder (){
        this.configuration=new Configuration();
    }
    /**
     *  该方法就是将配置文件进行解析封装为Configuration
     * @param inputStream
     * @return
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        //使用dom4j进行配置解析
        Document document = new SAXReader().read(inputStream);

        //<configuration>
        Element rootElement=document.getRootElement();
        List<Element> list=rootElement.selectNodes("//property");
        Properties properties=new Properties();
        for (Element element : list) {
            String name=element.attributeValue("name");
            String value=element.attributeValue("value");
            properties.setProperty(name,value);
        }

        //使用连接池 c3p0连接池
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        this.configuration.setDataSouce(comboPooledDataSource);

        //mapper
        //拿到路径，然后加载成字节输入流在进行解析
        List<Element> mapperList = rootElement.selectNodes("//mapper");

        //循环解析每一个mapper文件
        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsSteam = Resources.getResourceAsStream(mapperPath);

        }

        return configuration;
    }
}
