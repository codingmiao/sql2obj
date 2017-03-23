package org.wowtools.sql2obj.expression.node.valueable;

import org.wowtools.sql2obj.expression.Expressionable;

/**
 * @author liuyu
 * @date 2017/3/23
 */
public class SqlValueNode extends ValueableNode {
    private Comparable value;

    public SqlValueNode(Comparable value) {
        this.value = value;
    }

    @Override
    public Comparable getValue(Expressionable row) {
        return value;
    }

    @Override
    public String getSqlPart() {
        return value.toString();
    }
}
