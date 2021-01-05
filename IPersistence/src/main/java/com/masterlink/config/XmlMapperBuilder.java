package com.masterlink.config;

import com.masterlink.pojo.Configuration;
import com.masterlink.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @Classname XmlMapperBuilder
 * @Description TODO
 * @Date 2020/12/25 10:27
 * @Created by Klein
 */
public class XmlMapperBuilder {

    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration=configuration;
    }

    public void parseMapper(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        //获取根目录下的namespace以便于组装唯一id
        String namespace=rootElement.attributeValue("namespace");

        List<Element> list=rootElement.selectNodes("//select");

        for (Element element : list) {
            String id=element.attributeValue("id");
            String resultType=element.attributeValue("resultType");
            String parameterType=element.attributeValue("paramterType");
            String sqlText=element.getTextTrim();

            MappedStatement mappedStatement=new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sqlText);

            String key=namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);

        }


    }
}
