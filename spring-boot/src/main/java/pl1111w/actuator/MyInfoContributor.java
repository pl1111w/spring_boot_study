package pl1111w.actuator;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/28 18:03
 */
import java.util.Collections;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class MyInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("MyInfo", Collections.singletonMap("key", "value"));
    }

}


