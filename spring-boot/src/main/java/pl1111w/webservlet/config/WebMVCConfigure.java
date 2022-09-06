package pl1111w.webservlet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl1111w.webservlet.interceptor.MyInterceptor;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/15 10:31
 */
@Configuration
//@EnableWebMvc//全面接管mvc
public class WebMVCConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new MyInterceptor()).addPathPatterns("/2333");
    }

    /**
     * 接管RequestMappingHandlerMapping
     */
//    @Bean
//    public WebMvcRegistrations webMvcRegistrations(){
//        return new WebMvcRegistrations(){
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return WebMvcRegistrations.super.getRequestMappingHandlerMapping();
//            }
//        };
//    }
}
