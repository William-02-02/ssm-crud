package com.lyh.dao;

import com.lyh.pojo.Employee;
import com.lyh.pojo.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee row);

    int insertSelective(Employee row);
    
    List<Employee> selectByExampleWithDept(EmployeeExample example);
    
    Employee selectByPrimaryKeyWithDept(Integer empId);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("row") Employee row, @Param("example") EmployeeExample example);

    int updateByExample(@Param("row") Employee row, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee row);

    int updateByPrimaryKey(Employee row);
}