package com.cwh.mybatis.dao;

import com.cwh.mybatis.beans.Department;

/**
 * @author Cwh
 * CreateTime 2019/7/25 15:27
 */
public interface DepartmentMapper {

    public Department getDeptById(Integer id);

    public Department getDeptAndEmp(Integer id);

    public Department getDeptByIdStep(Integer id);
}
