/**
 * 
 */
package com.cqu.wb.exception;

/**
 * @author 汪波
 * @description 自定义异常
 */
public class CarException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer code;

	public CarException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMessage());
		this.code = exceptionEnum.getCode();		
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
