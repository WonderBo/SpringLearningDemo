/**
 * @description 数据库连接池配置文件的管理
 */
package com.cqu.db;

import com.cqu.util.ClassUtil;

public class DBPool {

	//保存数据库连接池的配置文件路径
	private String poolPath;
	
	//单例模式中构造方法是私有的（不能在类外调用进行实例化）
	private DBPool(){
		
	}
	//单例模式有以下特点：
	//　　1、单例类只能有一个实例。
	//　　2、单例类必须自己创建自己的唯一实例。
	//　　3、单例类必须给所有其他对象提供这一实例。
	//单例模式优点：1.实例控制  2.灵活性
	//单例模式缺点：1.开销  2.可能的开发混淆  3.对象生存期
	//单例模式：内部静态类(静态内部类实现单例模式)
	//创建唯一实例
	private static class DBPoolDao{
		private static DBPool dbPool = new DBPool();
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 返回DBPool对象
	 */
	//提供唯一实例
	public static DBPool getDBPool(){
		return DBPoolDao.dbPool;
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 获取数据库连接池的配置文件路径
	 */
	public String getPoolPath() {
		if(poolPath == null){
			//如果poolPath为空，赋值为默认值
			poolPath = ClassUtil.getClassRootPath(DBPool.class) + "proxool.xml";
		}
		return poolPath;
	}
	
	/**
	 * 
	 * @param poolPath
	 * @author 汪波
	 * @description 设置数据库连接池的配置文件路径
	 */
	public void setPoolPath(String poolPath) {
		this.poolPath = poolPath;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
