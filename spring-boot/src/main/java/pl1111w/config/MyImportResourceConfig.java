package pl1111w.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/16 21:05
 */

@Configuration
@ImportResource("classpath:bean.xml")
public class MyImportResourceConfig {

}
