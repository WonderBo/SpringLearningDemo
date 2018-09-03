/**
 * @description 数据库连接与关闭操作
 */
package com.cqu.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectionUtil {
	/**
	 * 
	 * @param resourcePath基础配置文件
	 * @return 连接会话
	 * @description 开启与数据库的连接
	 */
	public static SqlSession getSqlSession(String resourcePath) {
		if(resourcePath == null || resourcePath.equals("")) {
			return null;
		}
		SqlSession sqlSession = null;
		try {
			Reader reader = Resources.getResourceAsReader(resourcePath);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			sqlSession = sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSession;
	}
	
	/**
	 * 
	 * @param sqlSession连接会话
	 * @description 关闭与数据库的连接
	 */
	public static void closeSqlSession(SqlSession sqlSession) {
		sqlSession.close();
	}
}
