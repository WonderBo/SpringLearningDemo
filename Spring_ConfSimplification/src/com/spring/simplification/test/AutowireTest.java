/**
 * @description 测试conf-autowire.xml配置文件中的bean的自动装配
 */
package com.spring.simplification.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.simplification.bean.autowire.Customer;

public class AutowireTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/spring/simplification/conf/conf-autowire.xml");
		
		//测试未使用自动装配
		Customer customer_1 = applicationContext.getBean("customerWithoutAuto", Customer.class);
		customer_1.getPeople().sayTest();
		
		//测试使用byName自动装配
		Customer customer_2 = applicationContext.getBean("customerByName", Customer.class);
		customer_2.getPeople().sayTest();
		
		//测试使用byType自动装配
		Customer customer_3 = applicationContext.getBean("customerByType", Customer.class);
		customer_3.getPeople().sayTest();
		
		//测试使用constructor自动装配
		Customer customer_4 = applicationContext.getBean("customerByCons", Customer.class);
		customer_4.getPeople().sayTest();
	}

}
