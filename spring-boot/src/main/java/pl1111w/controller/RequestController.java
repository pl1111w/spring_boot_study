package pl1111w.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/3/24 11:11
 */
@RestController
public class RequestController {

    @GetMapping("getUser/{id}")
    public Map<String, String> getUser(@PathVariable("id") String ID) {
        Map<String, String> map = new HashMap<>();
        map.put(ID, "A000000");
        return map;
    }

    @GetMapping("getUser")
    public Map<String, String> getUser01(@RequestParam("id") String ID,HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        HttpSession session = request.getSession();
        session.setAttribute("user","fisher");
        map.put(ID, "A000001");
        return map;
    }

    @GetMapping("/getUser/{id}/name/{name}")
    public Map<String, String> getUser03(
            @PathVariable("id") String ID,
            @PathVariable("name") String name,
            @PathVariable Map<String, String> map) {
        return map;
    }

    @GetMapping("getUser/interest")
    public Map<String, String> getUser04(@RequestParam("interest") List<String> interests) {
        Map<String, String> map = new HashMap<>();
        map.put(interests.get(0), "swimming");
        map.put(interests.get(1), "running");
        return map;
    }

    @GetMapping("getUser/header")
    public Map<String, String> getUser05(@RequestHeader Map<String, String> map) {
        map.put("007", "222");
        return map;
    }

    @GetMapping("getUser/cookieValue")
    public Map<String, String> getUser06(HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("key", "money");
        httpServletResponse.addCookie(cookie);
        Map<String, String> map = new HashMap<>();
        return map;
    }

    @GetMapping("getUser/cookie")
    public Map<String, String> getUser06(@CookieValue("key") String cookie) {
        Map<String, String> map = new HashMap<>();
        map.put("007", cookie);
        return map;
    }

}
