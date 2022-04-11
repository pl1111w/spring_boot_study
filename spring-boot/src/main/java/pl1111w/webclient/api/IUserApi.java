package pl1111w.webclient.api;

import org.springframework.web.bind.annotation.*;
import pl1111w.webclient.bean.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/7 16:17
 */
@ApiServer("http://localhost:8080/user")
public interface IUserApi {
    @GetMapping("/")
    Flux<User> getAllUser();

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    @DeleteMapping("/{id}")
    Mono<Void> deleteUserById(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);
}
