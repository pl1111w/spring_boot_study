package pl1111w.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl1111w.config.MyConfigurationProperties;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/14 10:15
 */
@RestController
public class ControllerTest {

    @Autowired
    private MyConfigurationProperties carProperties;

    @GetMapping("/test")
    public String test() {
        return "Hello Spring Boot2";
    }

    @GetMapping("/car")
    public String car() {
        return carProperties.getColor();
    }
}
