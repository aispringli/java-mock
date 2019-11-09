package li.spring.redisson.service.impl;

import li.spring.redisson.model.UserAmount;
import li.spring.redisson.service.UserAmountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author spring.li
 * @date 2019/11/09
 **/
@SpringBootTest
public class TestUserAmountServiceImpl {
    @Autowired
    UserAmountService userAmountService;

    @Test
    void testInitUserAmount() throws InterruptedException {
        int threadNumber = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
        for (int i = 0; i < threadNumber; i++) {
            try {
                Thread.sleep(1000);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserAmount userAmount = new UserAmount(1, "test", 100);
                        userAmountService.initUserAmount(userAmount);
                        countDownLatch.countDown();
                    }
                }) {
                }.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countDownLatch.await();
        userAmountService.printUserAmount();
    }
}
