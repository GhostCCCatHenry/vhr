package org.javaboy.mailserver.develops;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 令牌桶实现方式
 */
public class TokenRateLimiter {
    private final int capacity;
    private final AtomicInteger tokens; // 当前令牌数量
    private final int refillRate; // 每秒填充的令牌数
    private final ScheduledExecutorService scheduler; // 定时任务执行器
    private final Semaphore semaphore; // 应对并发的信号量，设置为总令牌桶的50%
    private int scheduleTimes = 0;

    public TokenRateLimiter(int capacity, int refillRate, int period) {
        this.capacity = capacity;
        this.tokens = new AtomicInteger(capacity);
        this.refillRate = refillRate;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.semaphore = new Semaphore(capacity/2);
        scheduler.scheduleAtFixedRate(() -> {
            if (scheduleTimes == 60 / period) {
                tokens.set(capacity);
            }
            int currentTokens;
            int newTokens;
            do {
                currentTokens = tokens.get();
                newTokens = Math.min(capacity, currentTokens + refillRate);
            } while (!tokens.compareAndSet(currentTokens, newTokens));
            scheduleTimes++;
        }, 0, period, TimeUnit.SECONDS);
    }

    public boolean tryAcquire() {
        if (semaphore.tryAcquire()) {
            int currentTokens = tokens.get();
            if (currentTokens > 0) {
                return tokens.compareAndSet(currentTokens, currentTokens - 1); // 此处是处理完请求后才会释放信号量
            } else {
                semaphore.release();
                return false;
            }
        } else {
            return false;
        }
    }

    public void stop() {
        scheduler.shutdown();
        tokens.compareAndSet(tokens.get(), 0);
    }

    // 静态实现方式。
    public static class SemaphoreRateLimiter {
        private final Semaphore semaphore;

        public SemaphoreRateLimiter(int permits) {
            this.semaphore = new Semaphore(permits);
        }

        public void acquire() throws InterruptedException {
            semaphore.acquire();
        }

        public void release() {
            semaphore.release();
        }
    }
}
