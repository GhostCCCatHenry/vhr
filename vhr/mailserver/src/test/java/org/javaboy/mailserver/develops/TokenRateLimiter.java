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

    public TokenRateLimiter(int capacity, int refillRate, int period) {
        this.capacity = capacity;
        this.tokens = new AtomicInteger(capacity);
        this.refillRate = refillRate;
        this.scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            int currentTokens;
            int newTokens;
            do {
                currentTokens = tokens.get();
                newTokens = Math.min(capacity, currentTokens + refillRate);
            } while (!tokens.compareAndSet(currentTokens, newTokens));
        }, 0, period, TimeUnit.SECONDS);
    }

    public boolean tryAcquire() {
        int currentTokens = tokens.get();
        if (currentTokens > 0) {
            return tokens.compareAndSet(currentTokens, currentTokens - 1);
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
