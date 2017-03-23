package org.wowtools.sql2obj.expression;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.wowtools.sql2obj.expression.node.*;
import org.wowtools.sql2obj.expression.node.valueable.ColumnValueNode;
import org.wowtools.sql2obj.expression.node.valueable.SqlValueNode;

public class CatExpressionVisitor implements ExpressionVisitor {

    private ExpressionNode node = TrueNode.getInstance();

    public ExpressionNode getNode() {
        return node;
    }


    @Override
    public void visit(NullValue arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(Function arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(InverseExpression arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(JdbcParameter jp) {
        System.out.println("visit(JdbcParameter jp):" + jp.toString());
    }

    @Override
    public void visit(DoubleValue arg0) {
        node.addChild(new SqlValueNode(arg0.getValue()));

    }

    @Override
    public void visit(LongValue lv) {
        node.addChild(new SqlValueNode(lv.getValue()));
    }

    @Override
    public void visit(DateValue arg0) {
        node.addChild(new SqlValueNode(arg0.getValue()));

    }

    @Override
    public void visit(TimeValue arg0) {
        node.addChild(new SqlValueNode(arg0.getValue()));

    }

    @Override
    public void visit(TimestampValue arg0) {
        node.addChild(new SqlValueNode(arg0.getValue()));

    }

    @Override
    public void visit(Parenthesis arg0) {
        System.out.println("visit(Parenthesis arg0):" + arg0.toString());
        arg0.getExpression().accept(this);
    }

    @Override
    public void visit(StringValue sv) {
        node.addChild(new SqlValueNode(sv.getValue()));
    }

    @Override
    public void visit(Addition arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(Division arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(Multiplication arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(Subtraction arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(AndExpression ae) {
        node = new AndNode();

        CatExpressionVisitor leftVisitor = new CatExpressionVisitor();
        ae.getLeftExpression().accept(leftVisitor);
        node.setLeft(leftVisitor.getNode());

        CatExpressionVisitor rightVisitor = new CatExpressionVisitor();
        ae.getRightExpression().accept(rightVisitor);
        node.setRight(rightVisitor.getNode());

        if (node.getLeft() instanceof TrueNode) {
            //若一边为true,则将judger替换为另一边(优化性替换)
            node = node.getRight();
        } else if (node.getRight() instanceof TrueNode) {
            //若一边为true,则将judger替换为另一边(优化性替换)
            node = node.getLeft();
        } else if (node.getLeft() instanceof FalseNode ||
                node.getRight() instanceof FalseNode) {
            //若一边为false,则将judger替换为false(优化性替换)
            node = FalseNode.getInstance();
        }
    }

    @Override
    public void visit(OrExpression oe) {
        System.out.println("visit(OrExpression oe):" + oe.toString());
        node = new OrNode();

        CatExpressionVisitor leftVisitor = new CatExpressionVisitor();
        leftVisitor.node = FalseNode.getInstance();//or的两边初始化为false
        oe.getLeftExpression().accept(leftVisitor);
        node.setLeft(leftVisitor.getNode());

        CatExpressionVisitor rightVisitor = new CatExpressionVisitor();
        rightVisitor.node = FalseNode.getInstance();//or的两边初始化为false
        oe.getRightExpression().accept(rightVisitor);
        node.setRight(rightVisitor.getNode());

        if (node.getLeft() instanceof FalseNode &&
                node.getRight() instanceof FalseNode) {
            /*
             * 若left和right都为false，则将judger换为TrueJudger
			 * (为了避免"select * from t1,t2 where t1.id=1 or t1.id = 2"一类的sql在分析t2表条件时产生的错误)
			 * */
            node = TrueNode.getInstance();
        } else if (node.getLeft() instanceof FalseNode) {
            //若一边为false,则将judger替换为另一边(优化性替换)
            node = node.getRight();
        } else if (node.getRight() instanceof FalseNode) {
            //若一边为false,则将judger替换为另一边(优化性替换)
            node = node.getLeft();
        }
    }

    @Override
    public void visit(Between arg0) {
        System.out.println("visit(Between arg0):" + arg0.toString());

    }

    @Override
    public void visit(EqualsTo et) {
        System.out.println("visit(EqualsTo et):" + et.toString());
        node = new EqualsNode();

        et.getLeftExpression().accept(this);
        et.getRightExpression().accept(this);
    }

    @Override
    public void visit(GreaterThan gt) {
        System.out.println("visit(GreaterThan gt):" + gt.toString());
        node = new GreaterThanNode();
        gt.getLeftExpression().accept(this);
        gt.getRightExpression().accept(this);
    }

    @Override
    public void visit(GreaterThanEquals arg0) {
        System.out.println("visit(GreaterThanEquals arg0):" + arg0.toString());

    }

    @Override
    public void visit(InExpression arg0) {
        System.out.println("visit(InExpression arg0):" + arg0.toString());

    }

    @Override
    public void visit(IsNullExpression arg0) {
        System.out.println("visit(IsNullExpression arg0):" + arg0.toString());

    }

    @Override
    public void visit(LikeExpression arg0) {
        System.out.println("visit(LikeExpression arg0):" + arg0.toString());

    }

    @Override
    public void visit(MinorThan mt) {
        node = new MinorThanNode();
        mt.getLeftExpression().accept(this);
        mt.getRightExpression().accept(this);
    }

    @Override
    public void visit(MinorThanEquals arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(NotEqualsTo arg0) {
        node = new NotEqualsNode();
        arg0.getLeftExpression().accept(this);
        arg0.getRightExpression().accept(this);

    }

    @Override
    public void visit(Column c) {
        node.addChild(new ColumnValueNode(c.getColumnName()));
    }

    @Override
    public void visit(SubSelect arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void visit(CaseExpression arg0) {
        System.out.println("visit(CaseExpression arg0):" + arg0.toString());

    }

    @Override
    public void visit(WhenClause arg0) {
        System.out.println("visit(WhenClause arg0):" + arg0.toString());

    }

    @Override
    public void visit(ExistsExpression arg0) {
        System.out.println("visit(ExistsExpression arg0):" + arg0.toString());

    }

    @Override
    public void visit(AllComparisonExpression arg0) {
        System.out.println("visit(AllComparisonExpression arg0):" + arg0.toString());

    }

    @Override
    public void visit(AnyComparisonExpression arg0) {
        System.out.println("visit(AnyComparisonExpression arg0):" + arg0.toString());

    }

    @Override
    public void visit(Concat arg0) {
        System.out.println("visit(Concat arg0):" + arg0.toString());

    }

    @Override
    public void visit(Matches arg0) {
        System.out.println("visit(Matches arg0):" + arg0.toString());

    }

    @Override
    public void visit(BitwiseAnd arg0) {
        System.out.println("visit(BitwiseAnd arg0):" + arg0.toString());
    }

    @Override
    public void visit(BitwiseOr arg0) {
        System.out.println("visit(BitwiseOr arg0):" + arg0.toString());

    }

    @Override
    public void visit(BitwiseXor arg0) {
        System.out.println("visit(BitwiseXor arg0):" + arg0.toString());

    }

}
