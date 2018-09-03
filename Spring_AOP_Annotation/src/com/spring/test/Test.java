/**
 * @description 注解方式实现AOP代理 测试
 */
package com.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDAO dao = (UserDAO) context.getBean("userDAO");
		dao.delete("kangkang");
		System.out.println();
		dao.insert();
	}

}
