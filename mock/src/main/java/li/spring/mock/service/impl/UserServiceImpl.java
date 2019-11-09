package li.spring.mock.service.impl;

import li.spring.mock.mapper.UserMapper;
import li.spring.mock.model.User;
import li.spring.mock.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author lichunying
 * @date 2019/10/23
 **/
@Component
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        Assert.isTrue(userMapper.addUser(user) > 0, "添加失败");
        return new User("我是新用户", "12345678");
    }
}
