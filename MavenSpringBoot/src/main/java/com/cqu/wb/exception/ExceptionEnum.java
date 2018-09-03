/**
 * 
 */
package com.cqu.wb.exception;

/**
 * @author 汪波
 * @description 异常枚举类，便于统一管理异常
 */
public enum ExceptionEnum {
	UNKONW_ERROR(-1, "未知错误"),
	SUCCESS(0, "成功"),
	LOW_LEVEL_CAR(100, "低端Car"),
	MIDDLE_LEVEL_CAR(101, "中端Car"),
	HIGH_LEVEL_CAR(102, "高端Car"),
	;
	
	private Integer code;
	private String message;
	
	ExceptionEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
}
