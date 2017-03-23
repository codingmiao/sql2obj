package org.wowtools.sql2obj.expression;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.wowtools.sql2obj.expression.node.ExpressionNode;

import java.io.StringReader;

/**
 * @author liuyu
 * @date 2017/3/23
 */
public class SqlParser {
    public static void main(String[] args) throws JSQLParserException {
        String sql = "select * from tb where a>0 and b = 5";
        CatSelectVisitor visitor = parse(sql);
        ExpressionNode rn = visitor.getRootExpressionNode();
        System.out.println(rn.getSqlPart());
    }

    public static CatSelectVisitor parse(String sql) throws JSQLParserException {
        CCJSqlParserManager pm = new CCJSqlParserManager();
        net.sf.jsqlparser.statement.Statement statement = pm.parse(new StringReader(sql));
        Select selectStatement = (Select) statement;
        SelectBody body = selectStatement.getSelectBody();
        CatSelectVisitor visitor = new CatSelectVisitor();
        body.accept(visitor);
        return visitor;
    }
}
