package com.nju.mystore.exception;

import com.nju.mystore.Log.Log;

/**
 * 你可以在这里自定义Exception
 */
public class MyStoreException extends RuntimeException {

    public MyStoreException(String message) {
        super(message);
    }

    private static MyStoreException record_exception(String str){
        MyStoreException exception = new MyStoreException(str);
        Log.record_log(exception.getMessage());
        return exception;
    }

    public static MyStoreException phoneAlreadyExists() {
        return record_exception("手机号已经存在!");
    }

    public static MyStoreException phoneOrPasswordError() {
        return record_exception("手机号或密码错误!");
    }

}

