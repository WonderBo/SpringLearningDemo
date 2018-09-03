/**
 * @description 目标类
 */
package com.spring.test;

public class UserDAOImpl implements UserDAO {
	@Override
	public String delete(String name) {

//		测试发生异常（空指针异常）
//		String str = null;
//		str.length();
		
		System.out.println("删除成功---");
		return "delete方法返回值";
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		System.out.println("插入成功---");
	}

}
