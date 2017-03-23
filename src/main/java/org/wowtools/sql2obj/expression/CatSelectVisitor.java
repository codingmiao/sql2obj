package org.wowtools.sql2obj.expression;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.Union;
import org.wowtools.sql2obj.expression.node.ExpressionNode;

import java.util.List;


public class CatSelectVisitor implements SelectVisitor {
    private CatExpressionVisitor ev;


    public ExpressionNode getRootExpressionNode() {
        return ev.getNode();
    }

    @Override
    public void visit(PlainSelect ps) {
        System.out.println("visit(PlainSelect ps)");
        if (ps.getFromItem() instanceof SubSelect) {
            //若是子查询，获取其子查询语句，递归调用
            SubSelect ss = (SubSelect) ps.getFromItem();
            ss.getSelectBody().accept(this);
            return;
        }
        ev = new CatExpressionVisitor();
        Expression where = ps.getWhere();
        if (null != where) {
            where.accept(ev);
        }


    }

    @Override
    public void visit(Union union) {
        //union查询，遍历其每个子查询，调用visit(PlainSelect ps)方法
        List psList = union.getPlainSelects();
        for (int i = 0; i < psList.size(); i++) {
            PlainSelect ps = (PlainSelect) psList.get(i);
            visit(ps);
        }

    }
}



