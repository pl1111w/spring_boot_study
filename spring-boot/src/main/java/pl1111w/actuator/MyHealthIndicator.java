package pl1111w.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @title: pl1111w
 * @description: 自定义健康组件
 * @author: Kris
 * @date 2022/4/28 17:24
 */
@Component
public class MyHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        HashMap<String, Integer> hashMap = new HashMap<>();
        if (true) {
            builder.status(Status.UP);
            hashMap.put("count", 1);
            hashMap.put("ms", 100);
        } else {
            builder.status(Status.DOWN);
            hashMap.put("err", 1);
            hashMap.put("ms", 1000);
        }
        builder.withDetails(hashMap);
    }
}
