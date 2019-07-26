package com.cwh.mybatis;

import com.cwh.mybatis.beans.Department;
import com.cwh.mybatis.beans.Employee;
import com.cwh.mybatis.dao.DepartmentMapper;
import com.cwh.mybatis.dao.EmployeeMapper;
import com.cwh.mybatis.dao.EmployeeMapperAnnotation;
import com.cwh.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.URIParameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *1.接口式编程
 *      原生：              Dao    ====> DaoImpl
 *      mybatis：          Mapper  ====> xxMapper.xml
 *
 * 2.SqlSession代表和数据库的一次会话；用完必须关闭
 * 3.SqlSession和connection一样，他们都是非线程安全，每次使用都应该去获取新的对象
 * 4.Mapper接口没有实现类牡丹石mybatis会为这个接口生成一个代理对象
 *      (将接口和xml文件进行绑定)
 *      EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
 * 5.两个重要的配置文件：
 *      mybatis 的全局配置文件：包含数据库连接池信息，食物管理器信息等。。。系统运行环境信息
 *      sql映射文件：保证了每一个SQL语句的映射信息：
 *                      将sql抽取出来
 * @author Cwh
 * CreateTime 2019/7/24 11:58
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1.根据xml配置文件(全局配置文件)创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2.SQL映射文件，配置了每一个sql，以及sql的封装规则等。
     * 3.将SQL映射文件加入全局配置文件中。
     * 4.写代码
     * 1).根据全局配置文件得到SqlSessionFactory
     * 2).使用SqlSessionFactory，获取sqlSession对象，使用它来执行增删改查,一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 3).使用sql 的唯一标识符来告诉MyBatis执行哪一个sql，sql都保存在SQL映射文件中
     *
     */

    //1.根据xml配置文件(全局配置文件)创建一个SQLSessionFactory对象
    @Test
    public void test() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        //2.获取SQLSession实例，能直接执行已经映射的sql语句
        //selectOne("sql唯一标识符(sql配置文件的id)"，"SQL执行要有的参数")
        try {
            Employee employee = openSession.selectOne("selectEmp", 1);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }

    @Test
    public  void test01() throws IOException {
        //1.获取SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            //3.获取接口的实现类对象
            //会为接口自动创建一个动态代理对象，代理对象去执行增删改查
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
           //返回一个对象
            /*Employee employee = mapper.getEmpById(1);*/
            //代理对象
           /* System.out.println(mapper.getClass());*/
            //传递一个map对象
            /* Map<String,Object> map =new HashMap<>();
            map.put("id",1);
            map.put("lastName","Tom");
            map.put("tableName","table_employee");
            Employee employee = mapper.getEmpByMap(map);*/
            //返回一个list集合
           /* List<Employee> list = mapper.getEmpByLastName("%e%");
            for (Employee employee:list){
                System.out.println(employee);
            }*/

           //返回一个一条记录的map
           /* Map<String, Object> emp = mapper.getEmpByIdReturnMap(1);
            System.out.println(emp);*/

           //返回多条记录的map

           /* Map<String, Employee> employeeMap = mapper.getEmpByLastNameReturnMap("%r%");
            System.out.println(employeeMap);*/
        }finally {
            openSession.close();
        }
    }

    @Test
    public  void test02() throws IOException {
        //1.获取SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            //3.获取接口的实现类对象
            //会为接口自动创建一个动态代理对象，代理对象去执行增删改查
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(1);
            /* System.out.println(mapper.getClass());*/
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }

    /**
     *测试增删改
     * 1.mybatis允许增删改直接定义以下类型返回值
     *      Integer，Long，Boolean，void
     * 2.手动提交数据
     * sqlSessionFactory.openSession();         ===>手动提交
     * sqlSessionFactory.openSession(true);     ===>自动提交
     */
    @Test
    public  void test03() throws IOException {
        //1.获取SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            //3.获取接口的实现类对象
            //会为接口自动创建一个动态代理对象，代理对象去执行增删改查
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            /*Employee employee = mapper.getEmpById(1);
            System.out.println(employee);*/

            //添加数据
            Employee employee = new Employee(null, "Herry", "1", "Jerry@163.com");
            Long insertEmployee = mapper.insertEmployee(employee);
            System.out.println(employee.getId());

            //更改数据
/*            Employee employee = new Employee(1, "Jerry", "1", "Jerry@163.com");
            Integer updateEmployee = mapper.updateEmployee(employee);
            System.out.println(updateEmployee);*/

            //删除操作
/*            boolean deleteEmployeeById = mapper.deleteEmployeeById(3);
            System.out.println(deleteEmployeeById);*/
            //增删改后要手动提交
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test04() throws IOException {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession openSession = sessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            /*Employee employee = mapper.getEmpById(1);*/
           /* Employee employee = mapper.getEmpAndDept(1);*/
            Employee employee = mapper.getEmpByStepAndId(4);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
           /* Department department = mapper.getDeptAndEmp(1);*/
            Department department = mapper.getDeptByIdStep(1);
            System.out.println(department);
            System.out.println(department.getEmployees());
        }finally {
            openSession.close();
        }
    }
}
