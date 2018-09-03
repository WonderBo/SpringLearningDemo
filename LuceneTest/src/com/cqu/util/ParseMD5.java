/**
 * @Description MD5加密算法
 */
package com.cqu.util;

public class ParseMD5 extends Encrypt{
	
	/**
	 * 
	 * @param str
	 * @return
	 * @author 汪波
	 * @description 32位小写MD5
	 */
	public static String parseStrToMD5(String str){
		return encrypt(str, MD5);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 * @author 汪波
	 * @decription 32位大写MD5
	 */
	public static String parseStrToUpperMD5(String str){
		return parseStrToMD5(str).toUpperCase();
	}
	/**
	 * @param args
	 * @description 测试
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ParseMD5.parseStrToMD5("abcd"));
	}

}
