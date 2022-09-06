package mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl1111w.Application;
import pl1111w.database.mybatisplus.mappper.UserMapper;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/6/18 9:20
 */
@SpringBootTest(classes = Application.class)
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }

}
