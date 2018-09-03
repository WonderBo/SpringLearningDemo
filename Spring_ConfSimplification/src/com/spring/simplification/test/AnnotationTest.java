/**
 * @description 测试conf-annotation.xml配置文件中的基于注解的配置
 */
package com.spring.simplification.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.spring.simplification.bean.annotation.Boss;

public class AnnotationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//@Autowired,@Qualifier,@PostConstruct 和 @PreDestroy测试
		autowiredTest();
		
		//@Component测试
		componentTest();
	}
	
	public static void autowiredTest(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/spring/simplification/conf/conf-annotation-autowired.xml");
		
		Boss boss = applicationContext.getBean("boss", Boss.class);
		System.out.println(boss);
		
		//关闭 Spring 容器，以触发 Bean 销毁方法的执行
		applicationContext.destroy();
	}
	
	public static void componentTest(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/spring/simplification/conf/conf-annotation-component.xml");
		
		Boss boss = applicationContext.getBean("boss", Boss.class);
		System.out.println(boss);
	}
}
