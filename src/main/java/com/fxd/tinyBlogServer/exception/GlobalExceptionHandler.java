package com.fxd.tinyBlogServer.exception;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fxd.tinyBlogServer.pojo.inter.MetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

// 全局异常处理器
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 默认异常的状态码
     */
    private static final Integer DEFAULT_EXCEPTION = 500;

    /**
     * token超时异常状态码
     */
    private static final Integer TOKEN_ERROR_EXCEPTION = 505;

    /**
     * token无效状态码
     */
    private static final Integer TOKEN_EXPIRED_EXCEPTION = 506;


    /**
     * 处理token异常
     */
    @ResponseBody
    @ExceptionHandler({SignatureVerificationException.class, AlgorithmMismatchException.class, JWTDecodeException.class})
    public Map<String, Object> tokenErrorException() {
        Map<String, Object> res = new HashMap<>();
        res.put("meta", new MetaData(TOKEN_ERROR_EXCEPTION, "无效的token！"));
        log.error("无效的token");
        return res;
    }

    /**
     * 处理token异常
     */
    @ResponseBody
    @ExceptionHandler({TokenExpiredException.class})
    public Map<String, Object> tokenExpiredException() {
        Map<String, Object> res = new HashMap<>();
        res.put("meta", new MetaData(TOKEN_EXPIRED_EXCEPTION, "token超时！"));
        log.error("token超时");
        return res;
    }

    /**
     * 处理所有RuntimeException异常
     */
    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public Map<String, Object> allException(RuntimeException e) {
        Map<String, Object> res = new HashMap<>();
        res.put("meta", new MetaData(DEFAULT_EXCEPTION, e.getMessage()));
        log.error(e.getMessage());
        e.printStackTrace();
        return res;
    }

    /**
     * 处理所有Exception异常
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Map<String, Object> allException(Exception e) {
        Map<String, Object> res = new HashMap<>();
        res.put("meta", new MetaData(DEFAULT_EXCEPTION, e.getMessage()));
        log.error(e.getMessage());
        e.printStackTrace();
        return res;
    }
}
