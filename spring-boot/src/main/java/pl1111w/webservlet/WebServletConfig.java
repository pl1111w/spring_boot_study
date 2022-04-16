package pl1111w.webservlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/14 11:55
 */
@Configuration()
public class WebServletConfig {

    @Bean
    public ServletRegistrationBean myServletBean(){
        HttpServlet httpServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doGet(req, resp);
                resp.getWriter().write("my_servlet");
            }
        };
        return new ServletRegistrationBean(httpServlet,"/my_servlet");
    }

    @Bean
    public FilterRegistrationBean myFilerBean(){
        MyFilter filter = new MyFilter();
        return new FilterRegistrationBean(filter,myServletBean());
    }

    @Bean
    public ServletListenerRegistrationBean myListenerBean(){
        MyListener listener = new MyListener();
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean(listener);
        return bean;
    }
}
