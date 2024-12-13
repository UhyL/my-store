package com.nju.mystore.exception;

import com.nju.mystore.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 接收项目中所有抛出的异常，
 * 使用了RestControllerAdvice切面完成，
 * 表示所有异常出现后都会通过这里。
 * 这个类将异常信息封装到ResultVO中进行返回。
*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyStoreException.class)
    public ResultVO<String> handleAIExternalException(MyStoreException e) {
        e.printStackTrace();
        return ResultVO.buildFailure(e.getMessage());
    }
}
