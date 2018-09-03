/**
 * 
 */
package com.cqu.wb.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqu.wb.bean.Result;
import com.cqu.wb.exception.CarException;
import com.cqu.wb.util.ResultUtil;

/**
 * @author 汪波
 *
 */
// 使用 @ControllerAdvice + @ExceptionHandler 进行全局的 Controller 层异常统一处理, Controller 层不用进行 try-catch 
@ControllerAdvice
public class ExceprtionHandler {
	private final static Logger logger = LoggerFactory.getLogger(ExceprtionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result<Object> hanleException(Exception exception) {
		if(exception instanceof CarException) {
			CarException carException = (CarException) exception; 
			return ResultUtil.error(carException.getCode(), carException.getMessage());
		} else {
			logger.error("未知错误：{}", exception.getMessage());
			return ResultUtil.error(-1, "未知错误");
		}
	}
}
