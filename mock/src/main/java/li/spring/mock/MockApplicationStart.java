package li.spring.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试redis分布式所，这里单机多线程来模拟对账户的交易
 *
 * @author lichunying
 * @date 2019/10/23
 **/
@SpringBootApplication
public class MockApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(MockApplicationStart.class, args);
    }
}
