package com.cwh.mybatis;

import com.cwh.mybatis.beans.Department;
import com.cwh.mybatis.beans.Employee;
import com.cwh.mybatis.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

            List<Employee> emps = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2,3,4));
            for (Employee emp:emps){
                System.out.println(emp);
            }
        }finally {
            openSession.close();
        }
    }
    @Test
    public void DynamicTest02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps =new ArrayList<>();
            emps.add(new Employee(null,"Toms","1","Toms@163.com",new Department(1)));
            emps.add(new Employee(null,"PeiQian","0","PeiQian@163.com",new Department(1)));
            mapper.AddEmps(emps);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
    @Test
    public void TestInnerParameter() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee emp = new Employee();
            emp.setLastName("%r%");
            List<Employee> list = mapper.getEmpsTestInnerParameter(emp);
            for (Employee employee:list){
                System.out.println(employee);
            }

        }finally {
            openSession.close();
        }
    }
}
