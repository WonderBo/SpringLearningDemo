/**
 * @description SpEL运算符--比较运算
 */
package com.spring.spel.oper;

public class SpelComp {
	private boolean equal;
	private boolean lessThan;
	private boolean lessEqual;
	private boolean greaterThan;
	private boolean greaterEqual;
	
	public boolean isEqual() {
		return equal;
	}
	public void setEqual(boolean equal) {
		this.equal = equal;
	}
	public boolean isLessThan() {
		return lessThan;
	}
	public void setLessThan(boolean lessThan) {
		this.lessThan = lessThan;
	}
	public boolean isLessEqual() {
		return lessEqual;
	}
	public void setLessEqual(boolean lessEqual) {
		this.lessEqual = lessEqual;
	}
	public boolean isGreaterThan() {
		return greaterThan;
	}
	public void setGreaterThan(boolean greaterThan) {
		this.greaterThan = greaterThan;
	}
	public boolean isGreaterEqual() {
		return greaterEqual;
	}
	public void setGreaterEqual(boolean greaterEqual) {
		this.greaterEqual = greaterEqual;
	}
	
}
