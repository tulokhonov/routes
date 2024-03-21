package com.example.test2.dto;

import java.util.concurrent.Callable;

public class CallableObj<T> implements Callable<T> {

    @Override
    public T call() throws Exception {
        return null;
    }
}