package com.spring.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao dao = (UserDao) context.getBean("userDao");
		User user = (User) context.getBean("user");
		dao.save(user);
		List list = dao.list();
		System.out.println(list.size());
	}

}
