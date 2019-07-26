package com.cwh.mybatis;

import com.cwh.mybatis.beans.Employee;
import com.cwh.mybatis.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/7/26 11:19
 */
public class MyBatisDynamicSQLTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void DynamicTest01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee =new Employee(null,"%r%",null,null);
          /*  List<Employee> emps = mapper.getEmpsByConditionIf(employee);*/
            List<Employee> emps = mapper.getEmpsByConditionTrim(employee);
            for (Employee emp:emps){
                System.out.println(emp);
            }
        }finally {
            openSession.close();
        }
    }
}
