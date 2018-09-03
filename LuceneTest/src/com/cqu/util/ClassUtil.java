/**
 * @Description 返回class文件所在的目录
 */
package com.cqu.util;

public class ClassUtil {

	/**
	 * 
	 * @param c
	 * @return String 
	 * @Description 返回class文件所在的目录
	 */
	public static String getClassPath(Class<?> c){
		//该方法在windows上执行时会将空格转化为%20，因此得将其转回
		return c.getResource("").getPath().replaceAll("%20", " ");
	}
	
	/**
	 * 
	 * @param c
	 * @return String
	 * @Description 返回class文件所在项目的根目录（顶级目录）
	 */
	public static String getClassRootPath(Class<?> c){
		return c.getResource("/").getPath().replaceAll("%20", " ");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
