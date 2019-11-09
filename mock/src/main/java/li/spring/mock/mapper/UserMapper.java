package li.spring.mock.mapper;

import li.spring.mock.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author lichunying
 * @date 2019/10/23
 **/
@Mapper
@Component("userMapper")
public interface UserMapper {

    /**
     * 添加用户,在mock测试时并不会真正执行
     *
     * @param user
     * @return
     */
    @Insert("insert into user(username,password) values(#{username}, #{password})")
    Integer addUser(User user);
}
