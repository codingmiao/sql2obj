package org.wowtools.sql2obj.expression.node;

import org.wowtools.sql2obj.expression.Expressionable;

/**
 * 条件node
 */
public abstract class ExpressionNode {
    /**
     * 左子节点
     */
    protected ExpressionNode left;
    /**
     * 右子节点
     */
    protected ExpressionNode right;

    /**
     * 判断一条数据是否满足此node的条件
     *
     * @param row
     * @return 是否满足条件
     */
    public abstract boolean isMatched(Expressionable row);


    /**
     * 获得此node对应的sql片段(包含其子节点的内容)
     *
     * @return sqlPart
     */
    public abstract String getSqlPart();

    /**
     * 增加一个子条件，若left非空则left=node，否则right=node
     *
     * @param node node
     */
    public void addChild(ExpressionNode node) {
        if (left == null) {
            left = node;
        } else if (right == null) {
            right = node;
        } else {
            throw new RuntimeException("左右子节点都非空，不能再add");
        }
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public void setLeft(ExpressionNode left) {
        this.left = left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    public void setRight(ExpressionNode right) {
        this.right = right;
    }
}
