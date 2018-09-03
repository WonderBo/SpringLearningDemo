/**
 * @description 基于注解风格的SpEL配置
 */
package com.spring.spel.intro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		AnnoExpression helloBean1 = applicationContext.getBean("helloBean1", AnnoExpression.class);
		AnnoExpression helloBean2 = applicationContext.getBean("helloBean2", AnnoExpression.class);
		
		System.out.println("helloBean1：" + helloBean1.getStr());
		System.out.println("helloBean2：" + helloBean2.getStr());
	}

	//使用@Value注解指定SpEL表达式进行注入，该注解可以放到字段，方法及方法参数上
	//注意：当同时存在@Value注解注入与<property>进行setter注入时，setter注入将覆盖掉@Value注解值
	@Value("#{'Hello' + world}")
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
}
