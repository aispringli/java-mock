package li.spring.springstudy.service.impl;

import li.spring.springstudy.service.UserService;
import li.spring.springstudy.mapper.UserMapper;
import li.spring.springstudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author lichunying
 * @date 2019/10/23
 **/
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

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
