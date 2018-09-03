/**
 * @description 基于java类对spring中的bean进行配置测试
 */
package com.spring.simplification.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.simplification.bean.javaclass.ApplicationContextConfig;

public class JavaClassTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//启动Spring容器有如下3种方法
		//(1)通过构造函数加载java配置类
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		//(2)通过编码方式注册配置类
		AnnotationConfigApplicationContext applicationContext_2 = new AnnotationConfigApplicationContext();
		applicationContext_2.register(ApplicationContextConfig.class);
		applicationContext_2.refresh();
		//(3)通过引入多个配置类
		//@Configuration
		//@Import(....class)
		//public class XxxCongig{.....}
		
		String message = applicationContext.getBean("message", String.class);
		System.out.println(message);
	}

}
