package pl1111w.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl1111w.config.MyConfigurationProperties;
import pl1111w.config.MyYamlConfig;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/14 10:15
 */
@RestController
@Slf4j
public class ControllerTest {

    @Autowired
    private MyConfigurationProperties carProperties;
    @Autowired
    private MyYamlConfig yamlConfig;

    @GetMapping("/test")
    public String test() {
        return "Hello Spring Boot2";
    }

    @GetMapping("/car")
    public String car() {
        return carProperties.getColor();
    }

    @GetMapping("/person")
    public String person() {
        log.info(yamlConfig.toString());
        return yamlConfig.toString();
    }
}
