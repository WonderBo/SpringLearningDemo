/**
 * @description 反射机制class,method测试
 */
package com.spring.reflection.oper;

import java.awt.Button;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class ReflectionTest{
	
	/**
	 * 
	 * @throws Exception
	 * @description Class的getName()方法测试.
	 */
	public void getNameTest() throws Exception{
		System.out.println("===========begin getNameTest============");
		
		String name = "jack";
		Class stringClazz = name.getClass();
		System.out.println("String类名: " + stringClazz.getName());
		
		Button button = new Button();
		Class buttonClazz = button.getClass();
		System.out.println("Button类名: " + buttonClazz.getName());
		
		Class superButtonClazz = buttonClazz.getSuperclass();
		System.out.println("Button的父类名: " + superButtonClazz.getName());
		
		Class classTest = Class.forName("java.awt.Button");
		System.out.println("classTest name: " + classTest.getName());
		
		System.out.println("===========end getNameTest============");
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description Class的getMethod()方法测试
	 */
	public void getMethodTest() throws Exception{
		System.out.println("===========begin getMethodTest==========");
		
		Class clazz = Class.forName("com.spring.reflection.oper.ReflectionTest");
		
		Class[] ptypes = new Class[2];
		ptypes[0] = Class.forName("java.lang.String");
		ptypes[1] = Class.forName("java.util.Hashtable");
		// class.getMethod(方法名，参数数组)
		Method method = clazz.getMethod("testMethod", ptypes);
		
		Object[] args = new Object[2];
		args[0] = "hello, my dear";
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("name", "jack");
		args[1] = hashtable;
		// method.invoke（调用对象， 传参数组），返回该方法的返回值，同：调用对象.method(传参数组)
		String returnStr = (String) method.invoke(new ReflectionTest(), args);
		
		System.out.println("returnStr= " + returnStr);
		
		System.out.println("===========end getMethodTest==========");
	}
	
	/**
	 * 
	 * @param str
	 * @param hashtable
	 * @return String
	 * @description 测试方法
	 */
	public String testMethod(String str, Hashtable hashtable){
		String returnStr = "返回值";
		System.out.println("测试testMethod()方法调用");
        System.out.println("str= " + str);
        System.out.println("名字= " + (String) hashtable.get("name"));
        System.out.println("结束testMethod()方法调用");
        return returnStr;
	}
	
	public static void main(String[] args) throws Exception{
		ReflectionTest test = new ReflectionTest();
		test.getNameTest();
		System.out.println();
		test.getMethodTest();
		
	}
}