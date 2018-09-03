/**
 * @description 基于java类对spring中的bean进行配置
 */
package com.spring.simplification.bean.javaclass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//通过@Configuration注解的类将被作为配置类使用，表示在该类中将定义Bean配置元数据，且使用@Configuration注解的类本身也是一个Bean
@Configuration
public class ApplicationContextConfig {
	//通过@Bean注解配置类中的相应方法，默认该方法名为Bean名，该方法返回值为Bean对象，并定义Spring IoC容器如何实例化，自动装配，初始化Bean逻辑
	//注解格式@Bean(name={},autowire=Autowire.NO,initMethod="",destroyMethod="")
	//等价于xml配置：<bean id="message" class="java.lang.String"> <constructor-arg index="0" value="Hello world"/> </bean>
	@Bean
	public String message(){
		return "Hello world";	//同new String("Hello world")
	}
}

//基于java配置类和基于xml配置可以结合使用
//(1)java配置类引入xml配置：@ImportResource("classpath:com/.....xml")
//(2)xml配置引入java配置类：将java配置类作为一个bean引入即可（同一般的java类）