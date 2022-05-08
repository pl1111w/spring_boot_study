package pl1111w.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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

    Counter counter;

    public ControllerTest(MeterRegistry meterRegistry){ //该类的构造函数
        //MeterRegistry指标工厂
        counter = meterRegistry.counter("my.method.running.counter");//给指标气名称
    }

    @GetMapping("/test")
    public String test() {
        int a = 1 / 0;
        return "Hello Spring Boot2";
    }

    @GetMapping("/car")
    public String car() {
        counter.increment();
        return carProperties.getColor();
    }

    @GetMapping("/person")
    public String person() {
//        int a=1/0;  afterCompletion() method still executes
        log.info(yamlConfig.toString());
        return yamlConfig.toString();
    }
}
