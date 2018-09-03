/**
 * @description User映射类
 */
package com.cqu.bean;

import java.util.List;

public class User {
	private Integer id;
	private String userName;
	private String password;
	private Integer gender;
	private List<Book> bookList;
	
	//构造函数关乎类(属性)-表(字段)映射赋值（类实例化形成对象，对应表的某行数据进行映射赋值）
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
}
