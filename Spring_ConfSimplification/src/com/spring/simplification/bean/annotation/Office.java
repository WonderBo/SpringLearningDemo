/**
 * @description office类
 */
package com.spring.simplification.bean.annotation;

import org.springframework.stereotype.Component;

@Component("office")
public class Office {
	private String officeNo = "001";

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}
	
	@Override
	public String toString(){
		return "officeNo:" + officeNo;
	}
}
