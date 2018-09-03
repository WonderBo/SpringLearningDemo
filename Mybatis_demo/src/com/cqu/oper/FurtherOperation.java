/**
 * @description 数据库表的高级查询
 * 				关联查询：关键在于类的成员属性与数据库表的字段的映射处理（细节包括获取对象属性，赋值操作，映射关系）
 */
package com.cqu.oper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cqu.bean.Book;
import com.cqu.bean.FemaleUser;
import com.cqu.bean.MaleUser;
import com.cqu.bean.User;
import com.cqu.util.ConnectionUtil;

public class FurtherOperation {
	//联合查询：一次查询，但是资源占用比较大(表的关联)		时间复杂度：M		M：主查询对应表的数据行数
	//子查询：N+1次查询，占用资源可大可小(1次主查询对应N次子查询)	时间复杂度：M（1+N）	N：子查询对应表的数据行数
	/**
	 * @description 关联查询（属性/字段一一映射赋值[setter方法]）	->	联合查询
	 */
	public void selectAssociation() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<Book> bookList = sqlSession.selectList("selectBookJoin");
		for(Book book : bookList) {
			System.out.println("书名：" + book.getBookName() + "\t" + "用户名：" + book.getUser().getUserName());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description 关联查询(构造函数映射赋值)	->	构造查询
	 */
	public void selectAssociation2() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<Book> bookList = sqlSession.selectList("selectBookJoin2");
		for(Book book : bookList) {
			System.out.println("书名：" + book.getBookName() + "\t" + "用户名：" + book.getUser().getUserName());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description 关联查询(子查询返回对象属性)	->	子查询
	 */
	public void selectAssociation3() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<Book> bookList = sqlSession.selectList("selectBookJoin3");
		for(Book book : bookList) {
			//System.out.println("书名：" + book.getBookName() + "\t" + "用户名：" + book.getUser().getUserName());
			//延迟加载测试
			System.out.println("书名：" + book.getBookName());
			System.out.println("----延迟加载应用----");
			System.out.println("用户名：" + book.getUser().getUserName());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description 集合查询
	 */
	public void selectCollection() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<User> userList = sqlSession.selectList("selectUserWithColl");
		for(User user : userList) {
			System.out.println("用户名：" + user.getUserName());
			for(Book book : user.getBookList()) {
				System.out.println("书籍名：" + book.getBookName() + "\t价格：" + book.getPrice());
			}
			System.out.println();
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @param id
	 * @description 鉴别器
	 */
	public void selectDiscriminator(int id) {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		User user = sqlSession.selectOne("selectUserByDis", id);
		if(user instanceof MaleUser) {
			//向下转型
			MaleUser maleUser = (MaleUser)user;
			System.out.println("男性用户名：" + maleUser.getUserName() + "\t妻子名：" + maleUser.getWifeName());
		} else if(user instanceof FemaleUser) {
			FemaleUser femaleUser = (FemaleUser)user;
			System.out.println("女性用户名：" + femaleUser.getUserName() + "\t丈夫名：" + femaleUser.getHusbandName());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
}
