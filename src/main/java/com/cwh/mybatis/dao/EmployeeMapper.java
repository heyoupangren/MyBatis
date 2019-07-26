package com.cwh.mybatis.dao;

import com.cwh.mybatis.beans.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author Cwh
 * CreateTime 2019/7/24 14:38
 */
public interface EmployeeMapper {

    //返回多条记录的map；Map<String,Employee>：键就是你所设置的属性，值是记录封装后的JavaBean；
    @MapKey("lastName")
    public Map<String,Employee> getEmpByLastNameReturnMap(String lastName);

    //返回一条记录的map，key就是别名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> getEmpByLastName(String lastName);

    public Employee getEmpByMap(Map<String,Object> map);

    public Employee getEmpById(Integer id);

    public Integer updateEmployee(Employee employee);

    public Long insertEmployee(Employee employee);

    public boolean deleteEmployeeById(Integer id);
}
