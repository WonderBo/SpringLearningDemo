/**
 * @description SpEL工作原理
 */
package com.spring.spel.intro;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelTheory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//(1)定义解析器ExpressionParser
		ExpressionParser parser = new SpelExpressionParser();
		//(2)定义表达式Expression
		Expression expression = parser.parseExpression("('Hello' + 'World').concat(#end)");
		//(3.1)定义上下文对象EvaluationContext
		EvaluationContext context = new StandardEvaluationContext();
		//(3.2)构造上下文（参数赋值）
		context.setVariable("end", "!");
		//(4)根据表达式求值
		System.out.println(expression.getValue(context));
	}

}
