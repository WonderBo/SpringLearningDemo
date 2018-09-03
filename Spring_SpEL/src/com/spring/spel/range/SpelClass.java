/**
 * @description SpEL使用范围--类(eg.静态类的方法，常量)
 */
package com.spring.spel.range;

public class SpelClass {
	private float PI;	//满足 Sun JavaBean属性命名规范
	private float randomNumber;
	
	public float getPI() {
		return PI;
	}
	public void setPI(float pI) {
		PI = pI;
	}
	public float getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(float randomNumber) {
		this.randomNumber = randomNumber;
	}
	
}
