package com.cwh.mybatis.dao;

import com.cwh.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author Cwh
 * CreateTime 2019/7/24 16:58
 */
public interface EmployeeMapperAnnotation {

    @Select("select * from table_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
