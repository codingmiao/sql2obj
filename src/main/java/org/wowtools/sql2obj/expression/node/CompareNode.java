package org.wowtools.sql2obj.expression.node;

import org.wowtools.sql2obj.expression.ExpressException;
import org.wowtools.sql2obj.expression.Expressionable;
import org.wowtools.sql2obj.expression.node.valueable.ValueableNode;

/**
 * 抽象类，用于=,>,<等符号的比较
 **/
public abstract class CompareNode extends ExpressionNode {

    @Override
    public boolean isMatched(Expressionable row) {
        if (left instanceof ValueableNode && right instanceof ValueableNode) {
            ValueableNode left = (ValueableNode) this.left;
            ValueableNode right = (ValueableNode) this.right;
            int i = left.getValue(row).compareTo(right.getValue(row));
            return compareI(i);
        } else {
            throw new ExpressException("Compare类的子节点必须是ValueableNode");
        }
    }

    /**
     * 根据left.compareTo(right)的结果i，来决定是否正确
     *
     * @param i 通过 left.compareTo(right) 来“比较left和right的大小”。
     *          若返回“负数”，意味着“left比right小”；返回“零”，意味着“left等于right”；返回“正数”，意味着“left大于right”
     * @return
     */
    protected abstract boolean compareI(int i);

    @Override
    public String getSqlPart() {
        String msg = left.getSqlPart() + getOperator() + right.getSqlPart();
        return msg;
    }

    protected abstract String getOperator();
}
