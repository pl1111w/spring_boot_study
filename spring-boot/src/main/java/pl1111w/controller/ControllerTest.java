package pl1111w.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/14 10:15
 */
@RestController
public class ControllerTest {

    @GetMapping("/test")
    public String test(){
        return "Hello Spring Boot2";
    }
}
