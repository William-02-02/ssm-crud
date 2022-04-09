import com.lyh.dao.DepartmentMapper;
import com.lyh.dao.EmployeeMapper;
import com.lyh.pojo.Department;
import com.lyh.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;


/**
 * 使用spring的单元测试
 * 导入 SpringTest模块
 * @ContextConfiguration 指定Spring配置文件的位置
 * 使用junit runwith 注解指定运行的类
 * 直接autowired要使用的组件
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    
    @Autowired
    DepartmentMapper departmentMapper;
    
    @Autowired
    EmployeeMapper employeeMapper;
    
    @Autowired
    SqlSession sqlSession;
    
    
    
    
    @Test
    public void testCRUD(){
        
        // //创建ioc容器
        // ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // //从容器中获取mapper
        // ioc.getBean(DepartmentMapper.class);
        
        //插入几个部门
        // departmentMapper.insertSelective(new Department( null,"开发部"));
        // departmentMapper.insertSelective(new Department( null,"测试部"));
        
        //生成员工数据
        // employeeMapper.insertSelective(new Employee( null,"张三","男","zhangSan@qq.com",1));

        //批量插入 实现
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
    
        for (int i = 1000; i > 0; i--) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null,uid,"男",uid+"@qq.com",1));
        }
        System.out.println("批量执行完成");
    }
    
}
