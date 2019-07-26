package com.cwh.mybatis.dao;

import com.cwh.mybatis.beans.Employee;

import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/7/25 12:06
 */
public interface EmployeeMapperPlus {

    public List<Employee> getEmpByDespId(Integer id);

    public Employee getEmpByStep(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpById(Integer id);

    public Employee getEmpByStepAndId(Integer id);


}
