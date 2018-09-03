/**
 * @description Book映射类
 */
package com.cqu.bean;

public class Book {
	//int是基本类型(基本数据类型)，直接存数值,变量初始为0
	//Integer(复杂数据类型)是对象，用一个引用指向这个对象,初始化为null	
	private Integer id;
	private String bookName;
	private Integer price;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
