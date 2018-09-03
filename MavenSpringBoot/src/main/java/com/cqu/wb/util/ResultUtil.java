/**
 * 
 */
package com.cqu.wb.util;

import com.cqu.wb.bean.Result;

/**
 * @author 汪波
 * @description 封装输出结果
 */
public class ResultUtil {
	
	public static Result<Object> success(Object object) {
		Result<Object> result = new Result<Object>();
		result.setCode(0);
		result.setMessage("成功");
		result.setData(object);
		
		return result;
	}
	
	public static Result<Object> success() {
		return success(null);
	}
	
	public static Result<Object> error(Integer code, String message) {
		Result<Object> result = new Result<Object>();
		result.setCode(code);
		result.setMessage(message);
		
		return result;
	}
}
