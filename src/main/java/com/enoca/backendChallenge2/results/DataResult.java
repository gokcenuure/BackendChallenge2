package com.enoca.backendChallenge2.results;

import com.enoca.backendChallenge2.models.Customer;

import java.util.List;

public class DataResult<T> extends Result {

    private T data;

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data=data;
    }
    public DataResult(T data, boolean success) {
        super(success);
        this.data=data;
    }

    public T getData() {
        return this.data;
    }
}
