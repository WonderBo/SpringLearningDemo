/**
 * @description 数据库表的基础操作
 */
package com.cqu.oper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cqu.bean.Book;
import com.cqu.bean.User;
import com.cqu.conf.UserDeleteMapper;
import com.cqu.util.ConnectionUtil;

public class BasicOperation {
	/**
	 * @description 增添用户
	 */
	public void insertUser() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		User user = new User();
		user.setUserName("kang");
		user.setPassword("789");
		try {
			sqlSession.insert("insertUser", user);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 删除用户
	 */
	public void deleteUser() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		//获取映射配置接口
		UserDeleteMapper userDeleteMap = sqlSession.getMapper(UserDeleteMapper.class);
		try {
			userDeleteMap.deleteUser(4);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 按id查找
	 */
	public void findById() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		User user = sqlSession.selectOne("findById", 1);
		System.out.println("用户名：" + user.getUserName());
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 按用户名与密码查找(传入参数为HashMap)
	 */
	public void selectLogin() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("userName", "jack");
		hashMap.put("password", "123");
		User user = sqlSession.selectOne("selectLogin", hashMap);
		if(user != null) {
			System.out.println("登录成功");
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 按用户名与密码查找(传入参数为对象)
	 */
	public void selectLogin2() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		User parUser = new User();
		parUser.setUserName("jack");
		parUser.setPassword("123");
		User retUser = sqlSession.selectOne("selectLogin", parUser);
		if(retUser != null) {
			System.out.println("登录成功");
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 查找多条记录（返回多条记录是Mybatis自动封装成List）
	 */
	public void selectUser() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<User> listUser = sqlSession.selectList("selectUser");
		for(User user : listUser) {
			System.out.println("用户名：" + user.getUserName());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 查找多条记录，resultMap解决复杂查询时的映射问题
	 */
	public void selectUser2() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<User> listUser = sqlSession.selectList("selectUserByMap");
		for(User user : listUser) {
			System.out.println("用户名：" + user.getUserName());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 修改用户
	 */
	public void updateUser() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		User user = new User();
		user.setId(4);
		user.setUserName("mike");
		user.setPassword("666");
		try {
			sqlSession.insert("updateUser", user);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}

	/**
	 * @description 事务管理过程
	 */
	public void tran() {
		//获取数据库连接
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		try {
			//事务过程
			User user = new User();
			user.setUserName("wang");
			user.setPassword("666");
			sqlSession.insert("insertUser", user);

			Book book = new Book();
			book.setBookName("java");
			book.setPrice(89);
			book.setUser(user);
			sqlSession.insert("insertBook", book);

			//事务提交
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			//事务回滚
			sqlSession.rollback();
		} finally {
			//关闭数据库连接
			ConnectionUtil.closeSqlSession(sqlSession);
		}
	}
}
