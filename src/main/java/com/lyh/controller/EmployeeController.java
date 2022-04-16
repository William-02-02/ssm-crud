package com.lyh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.pojo.Employee;
import com.lyh.pojo.Msg;
import com.lyh.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
    
    /**
     * 查询员工数据 分页查询
     * @return
     */
    @RequestMapping("/empsOld")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")int pn , Model model){
        //不是分页
        //引入PageHelper分页插件
        //查询前只需要调用 传入页码 以及每页的大小
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        //使用PageInfo 包装结果   里面封装了分页的详细信息  5:连续显示的页数 就是导航一次显示多少页
        PageInfo pageInfo = new PageInfo(emps,5);
    
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        for (int navigatepageNum : navigatepageNums) {
            System.out.println(navigatepageNum);
        }
    
        model.addAttribute("pageInfo",pageInfo);
        
        return "list";
    }
    
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmp2(@RequestParam(value = "pn",defaultValue = "1")int pn){
        
        PageHelper.startPage(pn,5);
    
        List<Employee> emps = employeeService.getAll();
    
        PageInfo pageInfo = new PageInfo(emps,5);
        
        return Msg.success().add("pageInfo",pageInfo);
    }
    
    
    /**
     * 添加用户 返回值大于1则保存成功
     * Restful风格  根据请求 走不同处理
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp",method= RequestMethod.POST)
    @ResponseBody
    public Msg addEmp(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            System.out.println("校验出错");
            //封装错误信息
            HashMap<String, Object> error = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                //获取局域错误名称 和 默认错误信息
                //封装到error map中
                error.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
    
            return Msg.fail().add("errorFields",error);
        }else {
            int i = employeeService.addEmp(employee);
            if (i >=1){
                return Msg.success();
            }
        }
        
        return Msg.fail();
    }
    
    
    /**
     * 检验邮箱是否注册
     * true 可以注册
     * false 已注册
     */
    @RequestMapping("/checkEmail")
    @ResponseBody
    public Msg checkEmail(String empEmail){
        
        String regx = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";

        if (!empEmail.matches(regx)){
            return Msg.fail().add("va_msg","不符合邮箱格式checkEmail");
        }
        System.out.println(empEmail);
        boolean b = employeeService.checkEmail(empEmail);
        System.out.println("checkEmail:"+b);
        if (b){
            System.out.println("b: true");
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","邮箱已注册checkEmail");
        }
        
    }
    
    
    /**
     * 根据id查询用户信息
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")int id){
        //根据id查找用户信息的业务
        Employee employee  = employeeService.getEmp(id);
        
        return Msg.success().add("emp",employee);
    }
    
    
    /**
     * 根据id更新员工信息
     */
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){
        
        System.out.println(employee);
        employeeService.updateEmp(employee);
        
    
        return Msg.success();
    }
    
    
    /**
     * 根据id删除员工
     * 改造成批量删除
     */
    
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids")String str_ids){
        
        if (str_ids.contains("-")){
            //数组的格式是 1-22-31-4-5
            String[] str_id = str_ids.split("-");
            ArrayList<Integer> list_ids = new ArrayList<>();
            
            for (String s : str_id) {
                list_ids.add(Integer.parseInt(s));
            }
            
            employeeService.deleteBatch(list_ids);
            
        }else {
            
            int id = Integer.parseInt(str_ids);
            employeeService.deleteEmp(id);
        }
        
        return Msg.success();
    }
    
    
}
