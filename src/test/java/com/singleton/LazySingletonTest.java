package com.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class LazySingletonTest {

    @Test
    void getInstanceReturnsNonNull() {
        assertNotNull(LazySingleton.getInstance());
    }

    @Test
    void getInstanceReturnsSameInstance() {
        LazySingleton first = LazySingleton.getInstance();
        LazySingleton second = LazySingleton.getInstance();
        assertSame(first, second);
    }
}
