package org.wowtools.sql2obj.expression;

/**
 * 解析查询条件时产生的异常
 *
 * @author liuyu
 * @date 2017/3/23
 */
public class ExpressException extends RuntimeException {

    public ExpressException(String msg) {
        super(msg);
    }
}
