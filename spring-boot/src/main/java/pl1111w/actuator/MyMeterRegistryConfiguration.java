package pl1111w.actuator;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/28 18:09
 */
@Configuration(proxyBeanMethods = false)
public class MyMeterRegistryConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(Queue queue) {
        return (registry) -> Gauge.builder("MyMeterRegistryConfiguration", queue::size).register(registry);
    }

}
