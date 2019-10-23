package li.spring.springstudy.service;

import li.spring.springstudy.mapper.UserMapper;
import li.spring.springstudy.model.User;
import li.spring.springstudy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * service层的测试
 *
 * @author lichunying
 * @date 2019/10/23
 **/
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addUser1() {
        User user = new User("我是新用户", "123456");
        when(userMapper.addUser(any())).thenReturn(1);
        userService.addUser(user);
        System.out.println(user.toString());
    }

    @Test
    void addUser2() {
        User user = new User("我是新用户", "123456");
        when(userMapper.addUser(any())).thenReturn(0);
        //期望得到一个异常
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
        System.out.println(user.toString());
    }
}
