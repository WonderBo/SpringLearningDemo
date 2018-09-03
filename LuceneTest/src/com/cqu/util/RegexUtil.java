/**
 * @Description 正则表达式
 */
package com.cqu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	
	private static String ChRegex = "([\u4e00-\u9fa5]+)";
	private static String rootUrlRegex = "(http://.*?/)";
	private static String currentUrlRegex = "(http://.*/)";
	/**
	 * 
	 * @param dealStr(目标字符串)
	 * @param regexStr(正则表达式字符串)
	 * @param n(提取字符串在正则匹配结果中的位置)
	 * @return String(匹配结果)
	 * @author 汪波
	 * @Descrition 正则匹配，返回第一条匹配结果
	 */
	public static String getFirstString(String dealStr, String regexStr, int n){
		//参数合法性校验
		if(dealStr == null || regexStr == null || n<1){
			return "";
		}
		//Pattern是一个正则表达式经编译后的表现模式
		//Pattern.CASE_INSENSITIVE忽略大小写，Pattern.DOTALL忽略换行符
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		//以编译的正则表达式为基础对目标字符串进行匹配工作，多个Matcher是可以共用一个Pattern对象
		Matcher matcher = pattern.matcher(dealStr);
		while(matcher.find()){
			//trim()方法返回调用字符串对象的一个副本，但是所有起始和结尾的空格都被删除
			//group()捕获组
			return matcher.group(n).trim();
		}
		return "";
	}
	
	/**
	 * 
	 * @param dealStr
	 * @param regexStr
	 * @param n
	 * @return List<String>(含匹配结果的list)
	 * @author 汪波
	 * @Descrition 正则匹配，将相应捕获组的结果组装成List（对应捕获组存在多个结果）再返回
	 */
	public static List<String> getList(String dealStr, String regexStr, int n){
		List<String> list = new ArrayList<String>();
		if(dealStr == null || regexStr == null || n<1){
			return list;
		}
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		while(matcher.find()){
			list.add(matcher.group(n).trim());
		}
		return list;
	}
	
	/**
	 * 
	 * @param dealStr
	 * @param regexStr
	 * @param array(匹配结果位置数组) 数组包含捕获组的编号（数组大小对应提取信息数，即括号数）
	 * @return List<String[]> 
	 * @author 汪波
	 * @Description 将多个提取信息位置（用括号提取）的匹配结果加入到字符串数组再组装为List（对应捕获组存在多个结果）,最后返回
	 */
	public static List<String[]> getList(String dealStr, String regexStr, int[] array){
		List<String[]> list = new ArrayList<String[]>();
		//参数合法性校验
		if(dealStr == null || regexStr == null || array == null){
			return list;
		}
		//数组内容校验
		for(int i = 0; i<array.length; i++){
			if(array[i] < 1){
				return list;
			}
		}
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		while(matcher.find()){
			String[] ss = new String[array.length];
			for(int i = 0; i<array.length; i++){
				ss[i] = matcher.group(array[i]).trim();
			}
			list.add(ss);
		}
		return list;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author 汪波
	 * @description 将连接地址中的中文进行编码处理
	 */
	public static String encodeUrlCh(String url) throws UnsupportedEncodingException{
		while(true){
			String s = getFirstString(url, ChRegex, 1);
			if("".equals(s)){
				return url;
			}
			url = url.replaceAll(s, URLEncoder.encode(s, "utf-8"));
		}
	}
	
	/**
	 * 
	 * @param url 正则所匹配出来的内容
	 * @param currentUrl 当前url
	 * @return
	 * @author 汪波
	 * @description 组装网址，网页的url
	 */
	private static String getHttpUrl(String url, String currentUrl){
		try {
			//新增的replaceAll  转化有些地址接口中的转化地址，如： \/test\/1.html
			url = encodeUrlCh(url).replaceAll("\\\\/", "/");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(url.indexOf("http") == 0){
			return url;
		}
		if(url.indexOf("/") == 0){
			return getFirstString(currentUrl, rootUrlRegex, 1) + url.substring(1);
		}
		if(url.indexOf("\\/") == 0){
			return getFirstString(currentUrl, rootUrlRegex, 1) + url.substring(2);
		}
		return getFirstString(currentUrl, currentUrlRegex, 1) + url;
	}
	
	/**
	 * 
	 * @param dealStr 待处理字符串
	 * @param regexStr 正则表达式
	 * @param currentUrl 当前url
	 * @param n 提取内容在正则中的位置
	 * @return 
	 * @author 汪波
	 * @description 获取和正则匹配的绝对链接地址
	 */
	public static List<String> getArrayList(String dealStr, String regexStr, String currentUrl, int n){
		List<String> reArrayList = new ArrayList<String>();
		if(dealStr == null || regexStr == null || n<1 ||dealStr.isEmpty()){
			return reArrayList;
		}
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		while(matcher.find()){
			reArrayList.add(getHttpUrl(matcher.group(n).trim(), currentUrl));
		}
		return reArrayList;
	}
	
	/**
	 * 
	 * @param dealStr
	 * @param regexStr
	 * @param splitStr 分割连接符号
	 * @param n
	 * @return
	 * @author 汪波
	 * @description 正则匹配结果，每条记录用splitStr分割
	 */
	public static String getString(String dealStr, String regexStr, String splitStr, int n){
		String reStr = "";
		if(dealStr == null || regexStr == null || n < 1 || dealStr.isEmpty()){
			return reStr;
		}
		splitStr = (splitStr == null) ? "" : splitStr;
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		StringBuffer stringBuffer = new StringBuffer();
		//matcher.find()相当于一个迭代器（本例装了3个元素），执行find()一次，指针向后移动一位，若指针对应存在元素，则返回true，否则false
		while(matcher.find()){
			//n为捕获组对应的编号，当为1时，返回指针所对应的匹配字符串
			stringBuffer.append(matcher.group(n).trim());
			stringBuffer.append(splitStr);
		}
		reStr = stringBuffer.toString();
		if(splitStr != "" && reStr.endsWith(splitStr)){
			reStr = reStr.substring(0, reStr.length() - splitStr.length());
		}
		return reStr;
	}
	
	/**
	 * 
	 * @param dealStr
	 * @param regexStr
	 * @param n
	 * @return
	 * @author 汪波
	 * @description 正则匹配结果，将所有匹配记录组装成字符串
	 */
	public static String getString(String dealStr, String regexStr, int n){
		return getString(dealStr, regexStr, null, n);
	}
	
	/**
	 * @param args
	 * @author 汪波
	 * @Descrition 主函数
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dealStr = "adhvrjb37776avj4a";
		//提取的内容用小括号括起来
		String regexStr = "a(.*?)a(.*?)a";
		System.out.println(RegexUtil.getFirstString(dealStr, regexStr, 1));
	}

}
