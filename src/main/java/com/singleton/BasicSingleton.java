package com.singleton;

public class BasicSingleton {

    private static BasicSingleton INSTANCE = new BasicSingleton();

    private BasicSingleton() {
    }

    public static BasicSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BasicSingleton();
        }
        return INSTANCE;
    }
}
