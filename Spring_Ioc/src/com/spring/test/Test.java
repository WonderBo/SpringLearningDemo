package com.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bean.Customer;
import com.spring.bean.User;

public class Test {
	public static void main(String[] args) {
		//IOC：控制反转的意思就是将底层对程序拥有的控制权转移给了接口（配置文件），实现了接口控制的方式（类似于多态）
		//配置文件类似于一个容器，里面提供了一些bean（对应与每个class）的生产与联系，而这个容器类似于接口，从中获得bean的实例化
		//对于某个具体的对象而言，以前是它控制其他对象，现在是所有对象都被spring控制，所以这叫控制反转
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		String[] str = {"applicationContext.xml","applicationContext-2.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(str);
		
		User user = (User) context.getBean("user");		//通过id获取bean的实例
		Customer cu = (Customer) context.getBean("customer");
		
		System.out.println(user.getName());
		System.out.println(user.getAge());
		System.out.println(cu.getName());
	}
}
