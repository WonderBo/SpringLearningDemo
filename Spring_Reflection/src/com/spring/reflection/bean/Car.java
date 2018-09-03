/**
 * @description Car bean类
 */
package com.spring.reflection.bean;

public class Car {
	private String brand;
	private String color;
	private int maxSpeed;

	/**
	 * @author 汪波
	 * @description 默认构造方法
	 */
	public Car(){
		System.out.println("init Car...");
	}

	/**
	 * 
	 * @param brand
	 * @param color
	 * @param maxSpeed
	 * @description 带参构造方法
	 */
	public Car(String brand, String color, int maxSpeed){
		this.brand = brand;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @description 输出Car信息
	 */
	public void introduce() {
		System.out.println("brand:"+brand+";color:"+color+";maxSpeed:"+maxSpeed);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}


}
