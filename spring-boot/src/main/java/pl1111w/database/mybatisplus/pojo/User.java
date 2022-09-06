package pl1111w.database.mybatisplus.pojo;

import lombok.Data;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/6/18 9:16
 */
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}
