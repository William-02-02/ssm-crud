package com.lyh.controller;


import com.lyh.pojo.Department;
import com.lyh.pojo.Msg;
import com.lyh.service.DeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理与部门有关的请求
 */

@Controller
public class DepartmentController {
    
    @Autowired
    private DeparmentService deparmentService;
    
    
    /**
     * 返回所有 部门信息
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        
        //查出的所有部门信息
        List<Department> list = deparmentService.getDepts();
        for (Department department : list) {
            System.out.println(department);
        }
        
        return  Msg.success().add("depts",list);
    }
    
}
