/**
 * @description SpEL使用范围测试
 */
package com.spring.spel.range;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SpEL使用范围（字面值）测试");
		spelLiteralTest();
		System.out.println("SpEL使用范围（类）测试");
		spelClassTest();
		System.out.println("SpEL使用范围（Bean）测试");
		spelBeanTest();
	}
	
	private static void spelLiteralTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelLiteral spelLiteral = applicationContext.getBean("spelLiteral", SpelLiteral.class);
		
		System.out.println("count: " + spelLiteral.getCount());
		System.out.println("message: " + spelLiteral.getMessage());
		System.out.println("frequency: " + spelLiteral.getFrequency());
		System.out.println("capacity: " + spelLiteral.getCapacity());
		System.out.println("name1: " + spelLiteral.getName1());
		System.out.println("name2: " + spelLiteral.getName2());
		System.out.println("enabled: " + spelLiteral.isEnabled());
	}
	
	private static void spelClassTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelClass spelClass = applicationContext.getBean("spelClass", SpelClass.class);
		
		System.out.println("PI: " + spelClass.getPI());
		System.out.println("randomNumber: " + spelClass.getRandomNumber());
	}
	
	private static void spelBeanTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelBean spelBean1 = applicationContext.getBean("spelBean1", SpelBean.class);
		SpelBean spelBean2 = applicationContext.getBean("spelBean2", SpelBean.class);
		SpelBean spelBean3 = applicationContext.getBean("spelBean3", SpelBean.class);
		
		System.out.println("PI: " + spelBean1.getSong());
		System.out.println("PI: " + spelBean2.getSong());
		System.out.println("PI: " + spelBean3.getSong());
	}
}
