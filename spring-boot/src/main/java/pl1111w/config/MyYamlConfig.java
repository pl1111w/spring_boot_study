package pl1111w.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import pl1111w.bean.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: pl1111w
 * @description: read yaml
 * @author: Kris
 * @date 2022/3/20 13:24
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class MyYamlConfig {
    private String name;
    private int age;
    private String[] hobbies;
    private List<Car> cars;
    private Map<String, Integer> secretaries;
    private Map<String, HashMap<String, String>> animals;
}
