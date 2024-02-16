package org.javaboy.mailserver;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailserverApplicationTests {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    void contextLoads() {
    }

}