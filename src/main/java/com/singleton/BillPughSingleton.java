package com.singleton;

public class BillPughSingleton {

    private BillPughSingleton() {}

    private static class BillPughSingletonInner {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return BillPughSingletonInner.INSTANCE;
    }
}
