package li.spring.springstudy.service;

import li.spring.springstudy.model.User;
import org.springframework.stereotype.Service;

/**
 * @author lichunying
 * @date 2019/10/23
 **/
@Service
public interface UserService {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    User addUser(User user);
}
