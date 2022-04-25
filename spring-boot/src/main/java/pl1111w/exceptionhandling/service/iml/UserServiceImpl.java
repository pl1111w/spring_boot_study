package pl1111w.exceptionhandling.service.iml;

import org.springframework.stereotype.Service;
import pl1111w.exceptionhandling.entity.User;
import pl1111w.exceptionhandling.service.UserService;

import javax.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String addUser(@Valid User user) {
        // 直接编写业务逻辑
        return "success";
    }
}

