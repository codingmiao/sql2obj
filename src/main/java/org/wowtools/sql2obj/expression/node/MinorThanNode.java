package org.wowtools.sql2obj.expression.node;

/**
 * <
 **/
public class MinorThanNode extends CompareNode {

    @Override
    protected boolean compareI(int i) {
        return i < 0;
    }

    @Override
    protected String getOperator() {
        return "<";
    }
}
