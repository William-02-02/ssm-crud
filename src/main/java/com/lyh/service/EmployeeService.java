package com.lyh.service;

import com.lyh.dao.EmployeeMapper;
import com.lyh.pojo.Employee;
import com.lyh.pojo.EmployeeExample;
import com.lyh.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeMapper employeeMapper;
    
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }
    
    
    public int addEmp(Employee employee){
        return employeeMapper.insertSelective(employee);
    }
    
    
    public boolean checkEmail(String email){
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmailEqualTo(email);
        //不存在则可以添加 返回true
        return employeeMapper.countByExample(employeeExample)==0?true:false;
    }
    
    public Employee getEmp(int id) {
        
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }
    
    public void updateEmp(Employee employee) {
        
        employeeMapper.updateByPrimaryKeySelective(employee);
        
    }
    
    
    public void deleteEmp(int id) {
        
        employeeMapper.deleteByPrimaryKey(id);
    }
    
    public void deleteBatch(List<Integer> str_id) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdIn(str_id);
    
        employeeMapper.deleteByExample(employeeExample);
    }
}
