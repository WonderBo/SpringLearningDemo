/**
 * @description 未实现接口的类
 */
package com.spring.aop.cglib;

public class UserOperService {

	public String delete(String name) {
		// TODO Auto-generated method stub
		System.out.println("删除成功！");
		
		return "delete方法被调用";
	}

}
