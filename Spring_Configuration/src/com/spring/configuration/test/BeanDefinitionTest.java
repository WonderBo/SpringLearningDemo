/**
 * @description 测试conf-definition.xml配置文件中的bean的命名
 */
package com.spring.configuration.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.configuration.bean.definition.HelloWorld;
import com.spring.configuration.bean.definition.HelloWorldImpl;

public class BeanDefinitionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sayHelloWorldByMultiName();
	}	
	
	/**
	 * @description 直接根据类名获取实例对象，但是要求匹配的对象仅有一个
	 */
	public static void sayHelloWorldByClass() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-definition.xml");
		HelloWorld helloWorld = beanFactory.getBean(HelloWorldImpl.class);
		helloWorld.sayHello();
	}

	/**
	 * @description 根据id获取实例对象
	 */
	public static void sayHelloWorldById() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-definition.xml");
		HelloWorld helloWorld = beanFactory.getBean("helloWorldById", HelloWorld.class);
		helloWorld.sayHello();
	}
	
	/**
	 * @description 根据name获取实例对象
	 */
	public static void sayHelloWorldByName() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-definition.xml");
		HelloWorld helloWorld = beanFactory.getBean("helloWorldByName", HelloWorld.class);
		helloWorld.sayHello();
	}
	
	/**
	 * @description 根据name和id均可以获取实例对象
	 */
	public static void sayHelloWorldByNameAndId() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-definition.xml");
		HelloWorld helloWorld01 = beanFactory.getBean("helloWorldById2", HelloWorld.class);
		helloWorld01.sayHello();
		HelloWorld helloWorld02 = beanFactory.getBean("helloWorldByName2", HelloWorld.class);
		helloWorld02.sayHello();
		
	}
	
	/**
	 * @description 根据多个name获取实例对象
	 */
	public static void sayHelloWorldByMultiName() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-definition.xml");
		HelloWorld bean1 = beanFactory.getBean("bean1", HelloWorld.class);
		bean1.sayHello();
		HelloWorld bean11 = beanFactory.getBean("alias11", HelloWorld.class);
		bean11.sayHello();
		HelloWorld bean12 = beanFactory.getBean("alias12", HelloWorld.class);
		bean12.sayHello();
		HelloWorld bean13 = beanFactory.getBean("alias13", HelloWorld.class);
		bean13.sayHello();
		
		HelloWorld bean2 = beanFactory.getBean("bean2", HelloWorld.class);
		bean1.sayHello();
		HelloWorld bean21 = beanFactory.getBean("alias21", HelloWorld.class);
		bean21.sayHello();
		HelloWorld bean22 = beanFactory.getBean("alias22", HelloWorld.class);
		bean22.sayHello();
		HelloWorld bean23 = beanFactory.getBean("alias23", HelloWorld.class);
		bean23.sayHello();
	}
	
	/**
	 * @description 根据name与alias获取实例对象
	 */
	public static void sayHelloWorldByAlias() {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-definition.xml");
		HelloWorld bean3 = beanFactory.getBean("bean3", HelloWorld.class);
		HelloWorld bean31 = beanFactory.getBean("alias31", HelloWorld.class);
		HelloWorld bean32 = beanFactory.getBean("alias32", HelloWorld.class);
		bean3.sayHello();
		bean31.sayHello();
		bean32.sayHello();
	}
}
