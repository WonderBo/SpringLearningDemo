/**
 * @Description 流编码和文件编码检测
 */
package com.cqu.util;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

public class CharsetUtil {
	//（引入第三方jar包cpdetector）编码探测器
	private static final CodepageDetectorProxy detector;
	
	//初始化编码探测器
	static{
		detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		detector.add(JChardetFacade.getInstance());
	}
	
	/**
	 * 
	 * @param inputStream 输入流
	 * @param defaultCharset 默认编码格式
	 * @return string 编码格式
	 * @author 汪波
	 * @Description 检测InputStream的编码方式
	 */
	public static String getStreamCharset(InputStream inputStream, String defaultCharset){
		if(inputStream == null){
			return defaultCharset;
		}
		//定义局部变量并初始化
		int count = 200;
		try {
			//在读写操作前先得知数据流里有多少个字节可以读取（网络通信时注意间断性）
			count = inputStream.available();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			Charset charset = detector.detectCodepage(inputStream, count);
			if(charset != null){
				return charset.name();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return defaultCharset;
	}
	
	/**
	 * 
	 * @param url 文件url
	 * @param defaultCharset 默认编码格式
	 * @return string 编码格式
	 * @author 汪波
	 * @Description 检测URL下的编码方式，建议用于检测文件
	 */
	public static String getStreamCharset(URL url, String defaultCharset){
		if(url == null){
			return defaultCharset;
		}
		
		try {
			Charset charset = detector.detectCodepage(url);
			if(charset != null){
				return charset.name();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return defaultCharset;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
