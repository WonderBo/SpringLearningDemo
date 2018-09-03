/**
 * @description SpEL运算符测试
 */
package com.spring.spel.oper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OperTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SpEL运算符（数值运算）测试");
		spelMathTest();
		System.out.println("SpEL运算符（比较运算）测试");
		spelCompTest();
		System.out.println("SpEL运算符（逻辑运算）测试");
		spelLogicTest();
		System.out.println("SpEL运算符（条件运算）测试");
		spelCondTest();
		System.out.println("SpEL运算符（正则表达式匹配）测试");
		spelMatchTest();
	}
	
	private static void spelMathTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelMath spelMath = applicationContext.getBean("spelMath", SpelMath.class);
		
		System.out.println("adjustedAmount: " + spelMath.getAdjustedAmount());
		System.out.println("circumference: " + spelMath.getCircumference());
		System.out.println("average: " + spelMath.getAverage());
		System.out.println("remainder: " + spelMath.getRemainder());
		System.out.println("area: " + spelMath.getArea());
		System.out.println("fullName: " + spelMath.getFullName());
	}
	
	private static void spelCompTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelComp spelComp = applicationContext.getBean("spelComp", SpelComp.class);
		
		System.out.println("equal: " + spelComp.isEqual());
		System.out.println("lessThan: " + spelComp.isLessThan());
		System.out.println("lessEqual: " + spelComp.isLessEqual());
		System.out.println("greaterThan: " + spelComp.isGreaterThan());
		System.out.println("greaterEqual: " + spelComp.isGreaterEqual());
	}
	
	private static void spelLogicTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelLogic spelLogic = applicationContext.getBean("spelLogic", SpelLogic.class);
		
		System.out.println("and: " + spelLogic.isAnd());
		System.out.println("or: " + spelLogic.isOr());
		System.out.println("not: " + spelLogic.isNot());
	}
	
	private static void spelCondTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelCond spelCond = applicationContext.getBean("spelCond", SpelCond.class);
		
		System.out.println("myCount: " + spelCond.getMyCount());
		System.out.println("yourCount: " + spelCond.getYourCount());
	}
	
	private static void spelMatchTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelMatch spelMatch = applicationContext.getBean("spelMatch", SpelMatch.class);
		
		System.out.println("match: " + spelMatch.isMatch());
	}
}
