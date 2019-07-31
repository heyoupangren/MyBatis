package com.cwh.mybatis.dao;

import com.cwh.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/7/26 10:10
 */
public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee Employee);

    public List<Employee> getEmpsByConditionTrim(Employee Employee);

    public List<Employee> getEmpsByConditionChoose(Employee Employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    public void AddEmps(@Param("emps") List<Employee> employeeList);

    public List<Employee> getEmpsTestInnerParameter(Employee employee);
}
