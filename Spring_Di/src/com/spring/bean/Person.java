package com.spring.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Person {
	private String name;
	private Cat cat;
	
	private List list;
	private Set set;
	private Map map;
	private Properties pro;
	
	
	public Properties getPro() {
		return pro;
	}
	public void setPro(Properties pro) {
		this.pro = pro;
	}
	public String getName() {
		return name;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cat getCat() {
		return cat;
	}
	public void setCat(Cat cat) {
		this.cat = cat;
	}
	
}
