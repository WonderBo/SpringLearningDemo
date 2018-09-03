/**
 * @description 静态代理(代理类和目标类必须实现同一接口，由于实现特定接口，因此只能代理与该接口相关的目标类)
 */
package com.spring.aop.jdkproxy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogProxy implements UserDAO {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	private UserDAO dao;	//目标对象
	
	LogProxy(UserDAO dao){	
		this.dao = dao;
	}
	
	/**
	 * @description 相当于重写目标对象增强后的业务逻辑（或者说代理后的目标方法）
	 */
	@Override
	public String delete(String name) {
		// TODO Auto-generated method stub
		log.log(Level.INFO, "删除之前");
		String reStr = dao.delete(name);
		log.log(Level.INFO, "删除之后");
		
		return reStr;
	}
}
