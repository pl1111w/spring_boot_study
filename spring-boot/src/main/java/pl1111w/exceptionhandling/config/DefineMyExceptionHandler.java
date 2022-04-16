package pl1111w.exceptionhandling.config;

import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/13 17:51
 */
@Component
@Order(value=Integer.MIN_VALUE)
public class DefineMyExceptionHandler implements HandlerExceptionResolver {
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String data = "123456";
        //获取PrintWriter输出流
        PrintWriter out = httpServletResponse.getWriter();
        //使用PrintWriter流向客户端输出字符
        out.write(data);
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
