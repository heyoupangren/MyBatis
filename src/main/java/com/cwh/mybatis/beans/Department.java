package com.cwh.mybatis.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author Cwh
 * CreateTime 2019/7/25 14:26
 */
public class Department implements Serializable {
    private Integer id;
    private String departmentName;
    private List<Employee> employees;

    private static final long serialVersionUID=1L;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public Department(){}


    public Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Department(Integer id) {
        this.id = id;
    }
}

