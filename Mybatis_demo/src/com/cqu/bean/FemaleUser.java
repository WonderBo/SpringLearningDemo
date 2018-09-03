/**
 * @description 女性用户
 */
package com.cqu.bean;

public class FemaleUser extends User {
	private String husbandName;

	public String getHusbandName() {
		return husbandName;
	}
	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}
}
