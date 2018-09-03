/**
 * @description 接口实现类
 */
package com.spring.aop.advice;

public class UserDAOImpl implements UserDAO {

	/**
	 * 
	 * @param name
	 * @return
	 * @description 接口实现方法
	 */
	@Override
	public String delete(String name) {
//		throws与throw的区别：
//		1、throws出现在方法函数头；而throw出现在函数体。
//		2、throws表示出现异常的一种可能性，并不一定会发生这些异常；throw则是抛出了异常，执行throw则一定抛出了某种异常。
//		3、两者都是消极处理异常的方式（这里的消极并不是说这种方式不好），只是抛出或者可能抛出异常，但是不会由函数去处理异常，真正的处理异常由函数的上层调用处理。
		
//		throw new RuntimeException("删除发生异常！");
		
		System.out.println("删除成功！");
		return "delete方法被调用";
	}
	
}
