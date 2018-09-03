/**
 * @Description JavaBean与Xml格式的相互转换
 */
package com.cqu.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;


public class XmlUtil {

	private static final String noResult = "<result>no result</result>";
	
	/**
	 * 
	 * @param xml
	 * @return Document(dom4j包)
	 * @author 汪波
	 * @Description 将xml String对象转化为xml对象
	 */
	public static Document createFromString(String xml){
		try {
			return DocumentHelper.parseText(xml);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param object
	 * @return String
	 * @author 汪波
	 * @Description 将java对象转化为xml格式的字符串
	 */
	public static String parseObjectToXmlString(Object object){
		if(object == null){
			return noResult;
		}
		StringWriter stringWriter = new StringWriter();
		JAXBContext jAXBContext;
		Marshaller marshaller;
		try {
			jAXBContext = JAXBContext.newInstance(object.getClass());
			marshaller = jAXBContext.createMarshaller();
			marshaller.marshal(object, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return noResult;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
