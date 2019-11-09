package li.spring.mock.service;

import li.spring.mock.model.User;

/**
 * @author lichunying
 * @date 2019/10/23
 **/
public interface UserService {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    User addUser(User user);
}
