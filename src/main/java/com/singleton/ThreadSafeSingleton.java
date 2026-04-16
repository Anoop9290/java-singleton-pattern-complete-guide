package com.singleton;

import java.io.Serial;
import java.io.Serializable;

public class ThreadSafeSingleton implements Serializable,Cloneable {

    private static volatile ThreadSafeSingleton INSTANCE = null;

    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadSafeSingleton();
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public ThreadSafeSingleton clone() {
        throw  new UnsupportedOperationException();
    }
    @Serial
    protected Object readResolve(){
        return INSTANCE;
    }
}
