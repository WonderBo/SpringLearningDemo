/**
 * @description 数据库连接池的管理
 */
package com.cqu.db;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

public class DBManager {

	private DBManager(){
		try {
			//URLDecoder.decode(p, "UTF-8"))解决文件中文路径乱码问题
			//数据库连接池配置文件
			JAXPConfigurator.configure(URLDecoder.decode(DBPool.getDBPool().getPoolPath(), "UTF-8"), false);
			//数据库加载驱动类
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author 汪波
	 * @description 内部静态类实现单例模式 (由于static创建唯一实例)
	 * @version 1.1.0
	 */
	private static class DBManagerDao{
		private static DBManager dbManager = new DBManager();
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 提供唯一实例,返回数据库连接池管理类
	 */
	public static DBManager getDBManager(){
		return DBManagerDao.dbManager;
	}
	
	/**
	 * 
	 * @param poolName 连接池别名
	 * @return Connection 连接
	 * @throws SQLException
	 * @author 汪波
	 * @description 获取数据库连接
	 */
	public Connection getConnection(String poolName) throws SQLException{
		return DriverManager.getConnection(poolName);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
