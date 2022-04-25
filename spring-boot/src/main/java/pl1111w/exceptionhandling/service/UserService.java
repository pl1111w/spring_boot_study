package pl1111w.exceptionhandling.service;

import pl1111w.exceptionhandling.entity.User;

import javax.validation.Valid;

public interface UserService {
    /**
     *
     * @param user 用户对象
     * @return 成功则返回"success"，失败则返回错误信息
     */
    String addUser(@Valid User user);
}
