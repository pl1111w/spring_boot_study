package pl1111w.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl1111w.bean.Pet;
import pl1111w.bean.User;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/15 10:44
 */
@Configuration(proxyBeanMethods = false)
public class MyConfiguration {

    @Bean
    public User user001() {
        User user = new User();
        user.setAge(19);
        user.setName("kris");
        user.setPet(pet002());
        return user;
    }

    @Bean
    public Pet pet002() {
        Pet pet = new Pet();
        pet.setName("kala");
        return pet;
    }
}
