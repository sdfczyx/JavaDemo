package com.zyx.pattern.behavioral.interpreter2;

import java.util.HashMap;

public class SubExpression extends SymbolExpression{
	
	public SubExpression(Expression left, Expression right) {
		super(left, right); 
	}
	//求出 left 和 right 表达式相减后的结果
	public int interpreter(HashMap<String, Integer> var) {
		return super.left.interpreter(var) - super.right.interpreter(var); 
	}

}