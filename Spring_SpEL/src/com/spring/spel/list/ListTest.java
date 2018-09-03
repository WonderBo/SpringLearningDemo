/**
 * @description SpEL集合操作测试
 */
package com.spring.spel.list;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SpEL集合操作测试");
		spelListTest();
	}

	private static void spelListTest(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("com/spring/spel/conf/conf-spel.xml");
		
		SpelList spelList = applicationContext.getBean("spelList", SpelList.class);
		
		System.out.println("chosenCity1: " + spelList.getChosenCity1().getName());
		System.out.println("chosenCity2: " + spelList.getChosenCity2().getName());
		System.out.println("bigCities: " + spelList.getBigCities().size());
		System.out.println("oneBigCity1: " + spelList.getOneBigCity1().getName());
		System.out.println("oneBigCity2: " + spelList.getOneBigCity2().getName());
		for(int i=0; i<spelList.getCityNames1().size(); i++){
			System.out.println("cityNames1[" + i + "]:" + spelList.getCityNames1().get(i));
		}
		for(int i=0; i<spelList.getCityNames2().size(); i++){
			System.out.println("cityNames2[" + i + "]:" + spelList.getCityNames2().get(i));
		}
		for(int i=0; i<spelList.getCityNames3().size(); i++){
			System.out.println("cityNames3[" + i + "]:" + spelList.getCityNames3().get(i));
		}
	}
}
