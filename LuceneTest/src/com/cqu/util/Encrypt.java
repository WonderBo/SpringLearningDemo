/**
 * @Description 字符串加密
 */
package com.cqu.util;

import java.security.MessageDigest;

public class Encrypt {
	
	//MD5加密
	public static final String MD5 = "MD5";
	//SHA-1加密
	public static final String SHA1 = "SHA-1";
	//SHA-256加密
	public static final String SHA256 = "SHA-256";
	
	/**
	 * 
	 * @param str 加密前字符串
	 * @param encName 加密算法
	 * @return String 加密后的字符串
	 * @author 汪波
	 * @Description 对字符串加密
	 */
	public static String encrypt(String str, String encName){
		String reStr = null;
		try {
			//加密器
			MessageDigest digest = MessageDigest.getInstance(encName);
			byte[] bytes = digest.digest(str.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for(byte b : bytes){
				//b与11111111按位取与的结果，或者说保留b的低八位，返回Int类型
				//采用b&0xff而不用 Integer.toHexString(b)将byte强转为int的原因是：byte的大小为8bits，而int的大小为32bits
				int bt = b&0xff;
				//如果小于16，补位一个0
				if(bt < 16){
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return reStr;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
