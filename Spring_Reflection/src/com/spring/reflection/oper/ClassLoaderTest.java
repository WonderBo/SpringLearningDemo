/**
 * @description 3级ClassLoader：根ClassLoader-》EXTClassLoader-》APPClassLoader
 */
package com.spring.reflection.oper;

public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println("当前ClassLoader:" + loader);
		System.out.println("父ClassLoader:" + loader.getParent());
		System.out.println("祖父ClassLoader:" + loader.getParent().getParent());
	}

}
