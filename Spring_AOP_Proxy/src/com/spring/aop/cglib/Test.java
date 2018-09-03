/**
 * @description CGLib代理测试
 */
package com.spring.aop.cglib;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//CGLib代理
		//目标对象不需要实现接口，由bindTargetObject方法创建的代理对象只能强制转化为其设置继承的父类
		System.out.println("\n-------CGLib代理-------");
		Class<?> clazz = new CGLibLogProxy().bindTargetObject(new UserOperService()).getClass();
		for(int i=0; i<clazz.getInterfaces().length; i++){
			System.out.println("代理对象的接口：" + clazz.getInterfaces()[i].getName());
		}
		System.out.println("代理对象的类型：" + clazz.getName());
		System.out.println("代理对象的父类：" + clazz.getSuperclass().getName());
		
		UserOperService userOperService = (UserOperService) new CGLibLogProxy().bindTargetObject(new UserOperService());
		userOperService.delete("kangkang");
	}

}
