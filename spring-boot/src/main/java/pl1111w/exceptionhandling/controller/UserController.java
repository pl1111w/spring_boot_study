package pl1111w.exceptionhandling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl1111w.exceptionhandling.config.ExceptionResponseStatus;
import pl1111w.exceptionhandling.entity.User;
import pl1111w.exceptionhandling.exception.APIException;
import pl1111w.exceptionhandling.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping("exception")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String addUser() {
        if(true){
            throw new APIException(400, "这是400错误");
        }
        return "test error";
    }

    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        return user;
    }
    @GetMapping("/status")
    public void message() {
        if(LocalDate.now().isAfter(LocalDate.of(2022,1,1)))
       throw new ExceptionResponseStatus("time error");
    }
    @RequestMapping(value = "/helloResponseStatus")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED, reason = "402-PAYMENT_REQUIRED")
    public void helloResponseStatus() {
            System.out.println("======");
    }

}
