package org.wowtools.sql2obj.expression.node.valueable;

import org.wowtools.sql2obj.expression.ExpressException;
import org.wowtools.sql2obj.expression.Expressionable;
import org.wowtools.sql2obj.expression.node.ExpressionNode;

/**
 * 可转换为值的节点，分为三种，一种是sql中的值，一种是sql中表的列名，还有一种是函数
 *
 * @author liuyu
 * @date 2017/3/23
 */
public abstract class ValueableNode extends ExpressionNode {
    public abstract Comparable getValue(Expressionable row);

    @Override
    public boolean isMatched(Expressionable row) {
        throw new ExpressException("逻辑错误");
    }

}
