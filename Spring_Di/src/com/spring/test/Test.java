package com.spring.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bean.Cat;
import com.spring.bean.Person;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Cat cat = (Cat) context.getBean("cat");
		Person person = (Person) context.getBean("person");
		
		cat.say();
		
		System.out.println(person.getName());
		person.getCat().say();
		
		List list = person.getList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		Set set = person.getSet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		Map map = person.getMap();
		Set keys = map.keySet();
		Iterator itk = keys.iterator();
		while(itk.hasNext()){
			String key = (String) itk.next();
			String value = (String) map.get(key);
			System.out.println(key+":"+value);
		}
		
		Properties p = person.getPro();
		System.out.println(p.getProperty("tel"));
		System.out.println(p.getProperty("name"));
	}
}
