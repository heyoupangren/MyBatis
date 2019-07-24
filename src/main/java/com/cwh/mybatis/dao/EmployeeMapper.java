package com.cwh.mybatis.dao;

import com.cwh.mybatis.beans.Employee;

/**
 * @author Cwh
 * CreateTime 2019/7/24 14:38
 */
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public Integer updateEmployee(Employee employee);

    public Long insertEmployee(Employee employee);

    public boolean deleteEmployeeById(Integer id);
}
