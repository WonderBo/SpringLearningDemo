/**
 * @Description JavaBean与Json格式的相互转换
 */
package com.cqu.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	//默认json字符串，null值或错误的情况下返回该值（转义符\）
	private static final String noData = "{\"result\" : null}";
	private static ObjectMapper mapper;
	
	static{
		mapper = new ObjectMapper();
		//如果属性值为空，直接忽略
		mapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	/**
	 * 
	 * @param object
	 * @return String Json格式
	 * @author 汪波
	 * @Description 将object转化为json字符串
	 */
	public static String parseJson(Object object){
		//传入参数进行检测
		if(object == null){
			return noData;
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return noData;
		}
	}
	
	/**
	 * 
	 * @param json
	 * @return JsonNode
	 * @author 汪波
	 * @Description 将json字符串转化为JsonNode
	 */
	public static JsonNode jsonToObject(String json){
		try {
			return mapper.readTree(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
