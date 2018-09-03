/**
 * @Description HttpClient模拟浏览器
 */
package com.cqu.crawl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.cqu.util.CharsetUtil;

public abstract class CrawlBase {
	
	//日志
	private static Logger logger = Logger.getLogger(CrawlBase.class);
	//链接源代码
	private  String pageSourceCode = "";
	//响应头信息
	private Header[] responseHeaders = null;
	//连接超时时间ms
	private static int connectTimeOut = 10000;
	//连接读取时间ms
	private static int readTimeOut = 10000;
	//默认最大访问次数
	private static int maxConnectTimes = 3;
	//网页默认编码方式
	private static String charsetName = "iso-8859-1";
	//将HttpClient委托给MultiThreadedHttpConnectionManager，支持多线程
	private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
	private static HttpClient httpClient = new HttpClient(httpConnectionManager);
	//对httpClient做初始化操作
	static{
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectTimeOut);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(readTimeOut);
		//设置请求的编码格式
		httpClient.getParams().setContentCharset("utf-8");
	}
	
	/**
	 * 
	 * @param urlStr url链接
	 * @param params 请求头信息（以HashMap形式存储）
	 * @return GetMethod 含有请求信息的Get形式请求
	 * @author 汪波
	 * @Description 创建GET请求
	 */
	private GetMethod  createGetMethod(String urlStr, HashMap<String, String> params){
		GetMethod method = new GetMethod(urlStr);
		if(params == null){
			return method;
		}
		//利用迭代器获取HashMap结构（Entry）中的信息
		Iterator<Entry<String, String>> itor = params.entrySet().iterator();
		while(itor.hasNext()){
			Entry<String, String> entry =itor.next(); 
			String key = entry.getKey();
			String value = entry.getValue();
			method.setRequestHeader(key, value);
		}
		return method;
	}
	
	/**
	 * 
	 * @param urlStr url链接
	 * @param params 请求头信息（以HashMap形式存储）
	 * @return PostMethod 含有请求信息的Post形式请求
	 * @author 汪波
	 * @Description 创建POST请求
	 */
	private PostMethod  createPostMethod(String urlStr, HashMap<String, String> params){
		PostMethod method = new PostMethod(urlStr);
		if(params == null){
			return method;
		}
		Iterator<Entry<String, String>> itor = params.entrySet().iterator();
		while(itor.hasNext()){
			Entry<String, String> entry =itor.next(); 
			String key = entry.getKey();
			String value = entry.getValue();
			method.setRequestHeader(key, value);
		}
		return method;
	}
	
	/**
	 * @param method 请求方法
	 * @param defaultCharset 默认编码格式
	 * @param urlStr url链接
	 * @return boolean 成功或者失败
	 * @author 汪波 
	 * @Description: 执行HttpMethod，获取服务器返回的头信息和网页源代码
	 */
	private boolean readPage(HttpMethod method, String defaultCharset, String urlStr){
		//剩余连接次数
		int hasReadNum = maxConnectTimes;
		while(hasReadNum > 0){
			try{
				//判断返回状态是否是200
				if(httpClient.executeMethod(method) != HttpStatus.SC_OK){
					//日志写入
					logger.info("can`t connect " + urlStr + (maxConnectTimes - hasReadNum + 1));
					hasReadNum--;
				}else{
					//获取头信息
					responseHeaders = method.getResponseHeaders();
					//获取服务器的输出流（InputStream -> InputStreamReader -> BufferedReader -> String -> stringBuffer）
					InputStream inputStream = method.getResponseBodyAsStream();
					BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
					StringBuffer stringBuffer = new StringBuffer();
					String lineString = "";
					while((lineString = bufferReader.readLine()) != null){
						stringBuffer.append(lineString);
						stringBuffer.append("\n");
					}
					pageSourceCode = stringBuffer.toString();
					//检测流的编码方式
					//String.getBytes(charsetName)编码字符串转换成使用指定的字符集的字节序列，并将结果存储到一个新的字节数组
					InputStream in  = new ByteArrayInputStream(pageSourceCode.getBytes(charsetName));
					String charset = CharsetUtil.getStreamCharset(in, defaultCharset);
					//如果编码方式不同，则进行转码操作
					if(!charsetName.toLowerCase().equals(charset.toLowerCase())){
						pageSourceCode = new String(pageSourceCode.getBytes(charsetName), charset);
					}
					return true;
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error(urlStr + " can`t connect " + (maxConnectTimes - hasReadNum + 1));
				hasReadNum--;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param urlStr
	 * @param params
	 * @param charsetName
	 * @return boolean
	 * @author 汪波 
	 * @Description GET方式请求页面，并返回相关数据
	 */
	public boolean readPageByGet(String urlStr, HashMap<String, String> params, String charsetName){
		GetMethod method = createGetMethod(urlStr, params);
		return readPage(method, charsetName, urlStr);
	}
	
	/**
	 * 
	 * @param urlStr
	 * @param params
	 * @param charsetName
	 * @return boolean
	 * @author 汪波 
	 * @Description POST方式请求页面，并返回相关数据
	 */
	public boolean readPageByPost(String urlStr, HashMap<String, String> params, String charsetName){
		PostMethod method = createPostMethod(urlStr, params);
		return readPage(method, charsetName, urlStr);
	}
	
	/**
	 * @return String
	 * @author 汪波
	 * @Description 获取网页源代码
	 */
	public String getPageSourceCode() {
		return pageSourceCode;
	}

	/**
	 * @return Header[]
	 * @author 汪波  
	 * @Description 获取网页返回头信息
	 */
	public Header[] getResponseHeaders() {
		return responseHeaders;
	}

	/**
	 * @param timeout
	 * @author 汪波 
	 * @Description 设置连接超时时间
	 */
	public static void setConnectTimeOut(int connectTimeOut) {
		CrawlBase.connectTimeOut = connectTimeOut;
	}

	/**
	 * @param timeout
	 * @author 汪波 
	 * @Description 设置读取超时时间
	 */
	public static void setReadTimeOut(int readTimeOut) {
		CrawlBase.readTimeOut = readTimeOut;
	}

	/**
	 * @param maxConnectTimes
	 * @author 汪波 
	 * @Description 设置最大访问次数，链接失败的情况下使用
	 */
	public static void setMaxConnectTimes(int maxConnectTimes) {
		CrawlBase.maxConnectTimes = maxConnectTimes;
	}

	/**
	 * @param charsetName
	 * @author 汪波 
	 * @Description 设置默认编码方式
	 */
	public static void setCharsetName(String charsetName) {
		CrawlBase.charsetName = charsetName;
	}


	/**
	 * @param args
	 * @author 汪波
	 * @Description
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
