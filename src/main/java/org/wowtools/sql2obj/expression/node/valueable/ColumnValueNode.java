package org.wowtools.sql2obj.expression.node.valueable;

import org.wowtools.sql2obj.expression.Expressionable;

/**
 * @author liuyu
 * @date 2017/3/23
 */
public class ColumnValueNode extends ValueableNode {
    private String columnName;

    public ColumnValueNode(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public Comparable getValue(Expressionable row) {
        return row.getFieldValue(columnName);
    }

    @Override
    public String getSqlPart() {
        return columnName;
    }
}
