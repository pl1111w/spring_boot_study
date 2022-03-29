package pl1111w.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: pl1111w
 * @description: /car?price=50&brand=bwm,benz,audi  queryString
 * /car/price=50;brand=bwm,benz,audi  Matrix
 * @author: Kris
 * @date 2022/3/27 12:38
 */
@RestController
public class MatrixController {

    /**
     * URL 重新解决cookie禁用问题,springBoot默认禁用矩阵变量
     * /abc;jsessionid=XXX
     */
    //http://localhost:8080/books/42;a=11;b=eng;b=chn
    @GetMapping(value = "books/{bookId}")
    public Map<String, Object> getCookie(
            @MatrixVariable(value = "a", pathVar = "bookId") int q1,
            @MatrixVariable(value = "b") List<String> q2
    ) {

        Map<String, Object> map = new HashMap<>();
        map.put("bookId", q1);
        map.put("language", q2);
        return map;
    }
}
