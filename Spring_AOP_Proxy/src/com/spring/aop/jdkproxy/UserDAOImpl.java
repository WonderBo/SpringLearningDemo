/**
 * @description 接口实现类
 */
package com.spring.aop.jdkproxy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

//	Logger log = Logger.getLogger(this.getClass().getName());
	@Override
	public String delete(String name) {
		// TODO Auto-generated method stub
//		log.log(Level.INFO, "删除前");
		System.out.println("删除成功！");
		return "delete方法被调用";
//		log.log(Level.INFO, "删除后");
	}

}
