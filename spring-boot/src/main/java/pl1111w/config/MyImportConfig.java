package pl1111w.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl1111w.bean.User;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/15 12:13
 */
@Import(User.class)
@Configuration
public class MyImportConfig {

}
