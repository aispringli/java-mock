package li.spring.redisson.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author spring.li
 * @date 2019/11/09
 **/
@Slf4j
@Component
@Data
public class RedissonManager {

    @Value("${spring.redis.host}")
    protected String host;

    @Value("${spring.redis.port}")
    protected Integer port;

    @Value("${spring.redis.lockWaiteTime:3500}")
    Long lockWaiteTime;

    @Value("${spring.redis.lockTimeUnit:MILLISECONDS}")
    TimeUnit lockTimeUnit;


    private Config config = new Config();

    private Redisson redisson = null;

    public Redisson getRedisson() {
        return redisson;
    }

    @PostConstruct
    private void init() {
        try {
            config.useSingleServer().setAddress(host + ":" + port);
            redisson = (Redisson) Redisson.create(config);
            log.info("初始化Redisson结束");
        } catch (Exception e) {
            log.error("redisson init error", e);
        }
    }

}
