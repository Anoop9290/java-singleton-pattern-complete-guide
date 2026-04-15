package com.singleton;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThreadSafeSingletonTest {

    @Test
    void getInstanceReturnsNonNull() {
        assertNotNull(ThreadSafeSingleton.getInstance());
    }

    @Test
    void getInstanceReturnsSameInstance() {
        ThreadSafeSingleton first = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton second = ThreadSafeSingleton.getInstance();
        assertSame(first, second);
    }

    @Test
    void concurrentGetInstanceYieldsSingleInstance() throws InterruptedException {
        int threadCount = 32;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Set<ThreadSafeSingleton> distinct = ConcurrentHashMap.newKeySet();
        CountDownLatch ready = new CountDownLatch(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch finished = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                ready.countDown();
                try {
                    start.await();
                    for (int j = 0; j < 200; j++) {
                        distinct.add(ThreadSafeSingleton.getInstance());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    finished.countDown();
                }
            });
        }

        assertTrue(ready.await(5, TimeUnit.SECONDS), "workers should reach barrier");
        start.countDown();
        assertTrue(finished.await(30, TimeUnit.SECONDS), "workers should finish");
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));

        assertEquals(1, distinct.size(), "all threads must observe the same instance");
    }
}
