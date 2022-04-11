package pl1111w.webclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl1111w.webclient.api.IUserApi;
import pl1111w.webclient.bean.User;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/7 18:23
 */
@RestController
public class TestController {

    // 直接注入定义的接口
    @Resource
    IUserApi userApi;

    @GetMapping("/")
    public void test() {

        // 测试信息提取
        // 不订阅不会实际发出请求，但会进入我们的代理类
        userApi.getAllUser();
        userApi.getUserById("999");
        userApi.deleteUserById("777");
        userApi.createUser(Mono.just(User.builder().name("xfq").age(33).build()));

        // 直接调用实现调用rest接口的效果
//        Flux<User> users = userApi.getAllUser();
//        users.subscribe(System.out::println);
    }
}
