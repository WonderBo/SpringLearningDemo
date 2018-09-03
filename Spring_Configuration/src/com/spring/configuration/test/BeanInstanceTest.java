/**
 * @description 测试conf-instance.xml配置文件中的bean的实例化
 */
package com.spring.configuration.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.configuration.bean.instance.HelloWorld;

public class BeanInstanceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		helloWorldInstanceFactory();
	}

	/**
	 * @description 使用无参数构造器来实例化Bean(Bean-->对象)
	 */
	public static void sayHelloWithNoArgs(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-instance.xml");
		HelloWorld helloWorld = beanFactory.getBean("helloWorldWithNoArgs", HelloWorld.class);
		helloWorld.sayHello();
	}
	
	/**
	 * @description 使用有参数构造器来实例化Bean
	 */
	public static void sayHelloWithArgs(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-instance.xml");
		HelloWorld helloWorld = beanFactory.getBean("helloWorldWithArgs", HelloWorld.class);
		helloWorld.sayHello();
	}
	
	/**
	 * @description 使用静态工厂方法来实例化Bean（由于静态原因，无需在配置文件中先实例化工厂）
	 */
	public static void helloWorldStaticFactory(){
		// 1、读取配置文件，并实例化一个IOC容器
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-instance.xml");
		// 2、从容器中获取Bean（对象），注意此处完全“面向接口编程，而不是面向实现”
		HelloWorld helloWorld = beanFactory.getBean("helloWorldStaticFactory", HelloWorld.class);
		// 3、执行业务逻辑
		helloWorld.sayHello();
	}
	
	/**
	 * @description 使用实例化工厂方法来实例化Bean（需要在配置文件中先实例化工厂）
	 */
	public static void helloWorldInstanceFactory(){
		// 1、读取配置文件，并实例化一个IOC容器
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("com/spring/configuration/conf/conf-instance.xml");
		// 2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现编程”
		HelloWorld helloWorld = beanFactory.getBean("helloWorldInstance", HelloWorld.class);
		// 3、执行业务逻辑
		helloWorld.sayHello();
	}
}
