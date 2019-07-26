package com.cwh.mybatis.dao;

import com.cwh.mybatis.beans.Employee;

import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/7/26 10:10
 */
public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee Employee);

    public List<Employee> getEmpsByConditionTrim(Employee Employee);
}
