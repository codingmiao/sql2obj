package org.wowtools.sql2obj.expression.node;

import org.wowtools.sql2obj.expression.Expressionable;

/**
 * 总是返回true
 **/
public class TrueNode extends ExpressionNode {

    private static final TrueNode instance = new TrueNode();

    private TrueNode() {

    }

    public static TrueNode getInstance() {
        return instance;
    }


    @Override
    public boolean isMatched(Expressionable row) {
        return true;
    }

    @Override
    public String getSqlPart() {
        throw new UnsupportedOperationException();
    }
}
