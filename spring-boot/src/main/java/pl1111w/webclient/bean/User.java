package pl1111w.webclient.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/29 15:46
 */
@Data
@Builder
public class User {

    private String id;
    private String name;
    private int age;

}
