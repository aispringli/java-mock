package li.spring.redisson.service;

import li.spring.redisson.model.UserAmount;

/**
 * 用户账户操作
 *
 * @author spring.li
 * @date 2019/11/09
 **/
public interface UserAmountService {
    /**
     * 初始化账户信息
     *
     * @param userAmount
     * @return
     */
    Boolean initUserAmount(UserAmount userAmount);

    /**
     * 打印指定用户id的信息
     *
     * @param id
     */
    void printUserAmount(Integer id);

    /**
     * 打印所有账户信息
     */
    void printUserAmount();

    /**
     * 对用户账户信息进行操作
     *
     * @param id
     * @param money
     * @return
     */
    Boolean calUserAmount(Integer id, Integer money);
}
