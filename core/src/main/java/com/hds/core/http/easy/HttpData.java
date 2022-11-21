package com.hds.core.http.easy;


public interface HttpData<T> {

    T getData();

     String getMsg();

    /**
     * 是否请求成功
     */
    boolean isRequestSucceed();

    /**
     * 是否 Token 失效
     */
    boolean isTokenFailure();
}