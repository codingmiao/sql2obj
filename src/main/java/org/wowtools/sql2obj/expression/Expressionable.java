package org.wowtools.sql2obj.expression;

/**
 * 可作为条件判断的数据
 *
 * @author liuyu
 * @date 2017/3/23
 */
public interface Expressionable {

    /**
     * 获取此对象中某个字段的值
     *
     * @param fieldName 字段名
     * @return 字段值, 此值是可以比较大小的
     */
    Comparable getFieldValue(String fieldName);

}
