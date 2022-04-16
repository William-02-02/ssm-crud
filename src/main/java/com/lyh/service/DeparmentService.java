package com.lyh.service;


import com.lyh.dao.DepartmentMapper;
import com.lyh.pojo.Department;
import com.lyh.pojo.DepartmentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeparmentService {
    
    @Autowired
    private DepartmentMapper departmentMapper;
    
    
    public List<Department> getDepts() {
        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }
}
