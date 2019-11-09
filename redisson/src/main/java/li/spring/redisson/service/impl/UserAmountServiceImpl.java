package li.spring.redisson.service.impl;

import li.spring.redisson.config.RedissonManager;
import li.spring.redisson.model.UserAmount;
import li.spring.redisson.service.UserAmountService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author spring.li
 * @date 2019/11/09
 **/
@Slf4j
@Service
public class UserAmountServiceImpl implements UserAmountService {
    static List<UserAmount> userAmounts;

    private final static long LEASE_TIME = 5000;

    private final static String USER_AMOUNT_REDIS = "user_amount_redis_";

    private final RedissonManager redissonManager;

    public UserAmountServiceImpl(RedissonManager redissonManager) {
        this.redissonManager = redissonManager;
    }

    private UserAmount get(Integer id) {
        if (userAmounts == null || userAmounts.isEmpty()) {
            return null;
        }
        for (UserAmount userAmount : userAmounts) {
            if (userAmount.getId().equals(id)) {
                return userAmount;
            }
        }
        return null;
    }

    /**
     * 初始化账户信息
     *
     * @param userAmount
     * @return
     */
    @Override
    public Boolean initUserAmount(UserAmount userAmount) {
        if (userAmounts == null) {
            userAmounts = new LinkedList<>();
        }
        Assert.notNull(userAmount, "user amount not null");
        Assert.notNull(userAmount.getId(), "user amount id not null");
        UserAmount old = get(userAmount.getId());
        if (old != null) {
            log.warn("user amount id exist");
            return false;
        }

        RLock lock = redissonManager.getRedisson().getLock(USER_AMOUNT_REDIS + userAmount.getId());
        log.info("start to get lock : " + lock.isLocked());
        try {
            // LEASE_TIME 过期自动解锁
            if (!lock.tryLock(redissonManager.getLockWaiteTime(), LEASE_TIME, redissonManager.getLockTimeUnit())) {
                log.warn("获取锁失败,id:{}", userAmount.getId());
                return false;
            }
            try {
                Thread.sleep(6 * 1000);
                lock.lockInterruptibly();
                userAmounts.add(userAmount);
            } finally {
                log.info("" + lock.isLocked());
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                    log.info("unlock");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("发生错误", e);
        }
        return false;
    }

    /**
     * 打印指定用户id的信息
     *
     * @param id
     */
    @Override
    public void printUserAmount(Integer id) {

    }

    /**
     * 打印所有账户信息
     */
    @Override
    public void printUserAmount() {

    }

    /**
     * 对用户账户信息进行操作
     *
     * @param id
     * @param money
     * @return
     */
    @Override
    public Boolean calUserAmount(Integer id, Integer money) {
        return null;
    }
}
