package com.masterlink.sqlSession;

import com.masterlink.config.BoundSql;
import com.masterlink.pojo.Configuration;
import com.masterlink.pojo.MappedStatement;
import com.masterlink.utils.GenericTokenParser;
import com.masterlink.utils.ParameterMapping;
import com.masterlink.utils.ParameterMappingTokenHandler;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SimpleExectuor
 * @Description TODO
 * @Date 2020/12/30 19:54
 * @Created by Klein
 */
public class SimpleExectuor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        // 1.注册驱动，获取连接
        Connection connection=configuration.getDataSouce().getConnection();

        // 2.获取sql语句: select * from user where id=#{id} and username=#{username}
        //转换sql语句： select * from user where id = ? and username = ? ，转换的过程中，还需要对#{}里面的值进行解析存储
        String sql=mappedStatement.getSql();
        BoundSql boundSql=getBoundSql(sql);
        
        // 3.获取预处理对象: preparedStatement
        PreparedStatement preparedStatement=connection.prepareStatement(boundSql.getSqlText());
        
        // 4. 设置参数
        String parameterType= mappedStatement.getParameterType();
        Class<?> parameterTypeClass=getClassType(parameterType);

        List<ParameterMapping> parameterMappingList= boundSql.getParameterMappingList();

        for (int i = 0; i < parameterMappingList.size(); i++) {
            //通过反射来解析
            ParameterMapping parameterMapping=parameterMappingList.get(i);
            String content=parameterMapping.getContent();

            Field declaration=parameterTypeClass.getDeclaredField(content);

            declaration.setAccessible(true);
            //获取指定对象得指定字段
            Object o=declaration.get(params[0]);

            preparedStatement.setObject(i+1,o);
        }

        // 5. 执行sql
        ResultSet resultSet=preparedStatement.executeQuery();
        String resultType= mappedStatement.getResultType();
        Class<?> resultTypeClass=getClassType(resultType);

        ArrayList<Object> objects=new ArrayList<>();

        //6. 封装返回结果
        while (resultSet.next()) {
            Object o =resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData=resultSet.getMetaData();
            for (int i = 0; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName=metaData.getColumnName(i);
                // 字段得值
                Object value=resultSet.getObject(columnName);

                //使用反射或者内省，根据数据库表和实体得对应关系完成封装

            }

        }

        

        return null;
    }

    /**
     * 完成对#{}的解析工作: 1.将#{}使用？进行代替 2.解析出#{}里面的值进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类： 配置的标记解析器来完成针对占位符的处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler=new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser=new GenericTokenParser("#{","}",parameterMappingTokenHandler);
        //解析出来的sql
        String parseSql=genericTokenParser.parse(sql);

        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings=parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql=new BoundSql(parseSql,parameterMappings);
        return boundSql;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if(paramterType!=null){
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;

    }
}
