package pl1111w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import pl1111w.bean.Pet;
import pl1111w.bean.User;
import pl1111w.config.MyConditionConfig;

/**
 * @title: pl1111w
 * @description: spring boot launch
 * @author: Kris
 * @date 2022/3/14 10:14
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("get define configuration");
        User user001 = context.getBean("user001", User.class);
        User user0011 = context.getBean("user001", User.class);
        System.out.println(user001 == user0011);

        String[] beanNamesForType = context.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        boolean b = context.containsBean(String.valueOf(MyConditionConfig.class));
        if(b){
            MyConditionConfig bean = context.getBean(MyConditionConfig.class);
            Pet pet = bean.petInfo();
            if (pet!= null){
                System.out.println(pet.getName());
            }
        }
    }
}
