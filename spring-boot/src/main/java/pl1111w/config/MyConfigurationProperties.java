package pl1111w.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/16 21:18
 */
@Component
@ConfigurationProperties(prefix = "car")
public class MyConfigurationProperties {

    private String company;
    private String price;
    private String color;

    public String getCompany() {
        return company;
    }

    public String getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
