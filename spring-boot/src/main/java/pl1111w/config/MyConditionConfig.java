package pl1111w.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl1111w.bean.Pet;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/15 14:41
 */
@Configuration
//@ConditionalOnMissingBean(MyConditionConfig.class)
@ConditionalOnBean(MyConditionConfig.class)
public class MyConditionConfig {

    @Bean("pet")
    public Pet petInfo() {
        Pet pet = new Pet();
        pet.setName("mm");
        return pet;
    }
}
