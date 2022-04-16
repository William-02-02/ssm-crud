import com.github.pagehelper.PageInfo;
import com.lyh.pojo.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class MVCTest {
    
    //传入springmvc的ioc
    @Autowired
    WebApplicationContext context;        
    
    //虚拟mvc请求，获得处理结果
    MockMvc mockMvc;
    
    @Before  //每次用前都调用 初始化
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void testPage() throws Exception {
        //模拟请求 拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
        
        //请求成功后 请求域中存了pageInfo对象  可以去除进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo info = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码："+info.getPageNum());
        System.out.println("总页码："+info.getPages());
        System.out.println("总记录数："+info.getTotal());
        System.out.println("当前页面需要连续显示的页码：");
        int[] navigatepageNums = info.getNavigatepageNums();
        for (int navigatepageNum : navigatepageNums) {
            System.out.println(" "+navigatepageNum);
        }
        
        //获取员工数据
        List<Employee> list = info.getList();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    
    }
    
    
    
    
    
}
