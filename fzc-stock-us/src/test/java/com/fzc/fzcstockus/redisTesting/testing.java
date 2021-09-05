package com.fzc.fzcstockus.redisTesting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Flamenco.xxx
 * @date 2021/9/2 10:08
 */
@SpringBootTest
public class testing {




        @Autowired
        private StringRedisTemplate stringRedisTemplate;

        @Test
        public void testStringSetKey() {
            stringRedisTemplate.opsForValue().set("fzc", "love");
        }

}
