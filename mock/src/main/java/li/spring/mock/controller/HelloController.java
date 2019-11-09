package li.spring.mock.controller;

import li.spring.mock.model.User;
import li.spring.mock.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author lichunying
 * @date 2019/10/23
 **/
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(String name) {
        return "hello, " + name;
    }

    @GetMapping("/getUser")
    public String getUser(User user) {
        log.info("用户已存在: {}", user.toString());
        return user.toString();
    }

    @PostMapping("/addUser")
    public User addUser(User user) {
        log.info("用户添加成功: {}", user.toString());
        user = userService.addUser(user);
        return user;
    }


    @PostMapping("/addUserBody")
    public String addUserBody(@RequestBody String user) {
        log.info("用户添加成功: {}", user);
        return user;
    }

    @PostMapping("/addUserBodyJson")
    public User addUserBodyJson(@RequestBody User user) {
        log.info("用户添加成功: {}", user);
        user = userService.addUser(user);
        return user;
    }

}
