package pl1111w.database.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/23 18:00
 */
//配置类
//@Configuration
public class MyDataSourceConfig {

    @ConfigurationProperties("spring.datasource")//把配置文件的链接信息，绑定到返回的组件上
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setFilters("stat,wall");  //开始状态监控功能(stat)、防火墙（wall）
//        druidDataSource.setMaxActive(10);//活跃的线程数
        return druidDataSource;
    }

    @Bean
    //配置druid的监控页功能
    public ServletRegistrationBean servletRegistrationBean(){
        StatViewServlet statViewServlet = new StatViewServlet();//返回监控页，（原生的servlet类型的）
        //监控页面的具体设置

        //配置好拦截的路径(访问http://localhost:8080/druid/index.html，就会来到我们的监控页面)
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(statViewServlet,"/druid/*");
        //监控页面的登入+黑白名单（指定哪些ip可以进入）
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        return servletRegistrationBean;
    }
    //web监控数据的开启
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        WebStatFilter webStatFilter = new WebStatFilter();

        FilterRegistrationBean<WebStatFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        //不监控的数据
        filterFilterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterFilterRegistrationBean;
    }
}
