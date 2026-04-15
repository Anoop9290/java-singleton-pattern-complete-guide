package com.singleton;

public class BasicSingleton {


    public static BasicSingleton INSTANCE=null;

    private BasicSingleton(){

    }
     public BasicSingleton getInstance(){

        if(INSTANCE==null){
            INSTANCE=new BasicSingleton();
        }
        return INSTANCE;
     }

}
