/**
 * @description 基于Xml风格的SpEL配置
 */
package com.spring.spel.intro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		String hello1 = applicationContext.getBean("hello1", String.class);
		String hello2 = applicationContext.getBean("hello2", String.class);
		String hello3 = applicationContext.getBean("hello3", String.class);
		
		System.out.println("hello1；" + hello1);
		System.out.println("hello2：" + hello2);
		System.out.println("hello3：" + hello3);
	}

}
