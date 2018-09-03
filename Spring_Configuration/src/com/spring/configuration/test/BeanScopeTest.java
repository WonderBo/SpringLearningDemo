/**
 * @description 测试conf-scope.xml配置文件中的bean的作用域
 */
package com.spring.configuration.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.configuration.bean.scope.Boss;

public class BeanScopeTest {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-scope.xml");
		
		Boss boss1 = beanFactory.getBean("boss1", Boss.class);
		Boss boss2 = beanFactory.getBean("boss2", Boss.class);
		Boss boss3 = beanFactory.getBean("boss3", Boss.class);
		
		System.out.println(boss1.getCar());
		System.out.println(boss2.getCar());
		System.out.println(boss3.getCar());
	}
}
