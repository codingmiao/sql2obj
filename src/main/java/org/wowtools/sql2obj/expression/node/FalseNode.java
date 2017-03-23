package org.wowtools.sql2obj.expression.node;

import org.wowtools.sql2obj.expression.Expressionable;

/**
 * 总是返回false
 * **/
public class FalseNode extends ExpressionNode {
	private static final FalseNode instance = new FalseNode();
	
	private FalseNode(){
		
	}
	
	public static FalseNode getInstance(){
		return instance;
	}


	@Override
	public boolean isMatched(Expressionable row) {
		return false;
	}

	@Override
	public String getSqlPart() {
		throw new UnsupportedOperationException();
	}
}
