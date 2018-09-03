/**
 * @description 客户类
 */
package com.spring.simplification.bean.autowire;

public class Customer {
	private People people;

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}
	
	public Customer(People people) {
		this.people = people;
	}
	
	public Customer(){};
}
