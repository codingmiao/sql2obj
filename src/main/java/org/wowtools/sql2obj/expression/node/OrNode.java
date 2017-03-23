package org.wowtools.sql2obj.expression.node;

import org.wowtools.sql2obj.expression.Expressionable;

/**
 * or
 **/
public class OrNode extends ExpressionNode {

    @Override
    public boolean isMatched(Expressionable row) {
        return left.isMatched(row) || right.isMatched(row);
    }

    @Override
    public String getSqlPart() {
        String msg = "(" + left.getSqlPart() + " OR " + right.getSqlPart() + ")";
        return msg;
    }
}
