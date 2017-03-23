package org.wowtools.sql2obj.expression.node;

import org.wowtools.sql2obj.expression.Expressionable;

/**
 * and
 **/
public class AndNode extends ExpressionNode {

    @Override
    public boolean isMatched(Expressionable row) {
        return left.isMatched(row) && right.isMatched(row);
    }

    @Override
    public String getSqlPart() {
        String msg = "(" + left.getSqlPart() + " AND " + right.getSqlPart() + ")";
        return msg;
    }

}
