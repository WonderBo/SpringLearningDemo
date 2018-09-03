/**
 * @description 动态SQL语句操作
 */
package com.cqu.oper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cqu.bean.Book;
import com.cqu.util.ConnectionUtil;

public class DynamicSQLOperation {
	/**
	 * @description If操作-动态SQL
	 */
	public void selectIf() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		Book book = new Book();
		book.setPrice(70);
		List<Book> bookList = sqlSession.selectList("selectIf", book);
		for(Book bookInstance : bookList) {
			System.out.println("书名：" + bookInstance.getBookName() + "\t价格：" + bookInstance.getPrice());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description Choose操作-动态SQL
	 */
	public void selectChoose() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		Book book = new Book();
		//book.setId(1);
		book.setBookName("%p%");
		List<Book> bookList = sqlSession.selectList("selectChoose", book);
		for(Book bookInstance : bookList) {
			System.out.println("书籍ID："+ bookInstance.getId() + "\t书名：" 
					+ bookInstance.getBookName() + "\t价格：" + bookInstance.getPrice());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description Where操作-动态SQL
	 */
	public void selectWhere() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		Book book = new Book();
		//book.setBookName("%v%");
		book.setId(3);
		List<Book> bookList = sqlSession.selectList("selectWhere", book);
		for(Book bookInstance : bookList) {
			System.out.println("书籍ID："+ bookInstance.getId() + "\t书名：" 
					+ bookInstance.getBookName() + "\t价格：" + bookInstance.getPrice());
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description Set操作-动态SQL
	 */
	public void updateSet() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		Book book = new Book();
		book.setId(3);
		book.setBookName("thinkPHP");
		book.setPrice(57);
		try {
			sqlSession.update("updateSet", book);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description Trim操作-动态SQL
	 */
	public void updateTrim() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		Book book = new Book();
		book.setId(3);
		book.setBookName("thinkphp");
		book.setPrice(53);
		try {
			sqlSession.update("updateTrim", book);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
	
	/**
	 * @description Foreach操作(循环查询)-动态SQL
	 */
	public void selectForeach() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(3);
		List<Book> bookList = sqlSession.selectList("selectForeach", idList);
		for(Book book : bookList) {
			System.out.println("书籍ID："+ book.getId() + "\t书名：" 
					+ book.getBookName() + "\t价格：" + book.getPrice());
		}
	}
	
	/**
	 * @description Foreach操作(循环插入)-动态SQL
	 */
	public void insertForeach() {
		SqlSession sqlSession = ConnectionUtil.getSqlSession("com/cqu/conf/MyBatisConfig.xml");
		List<Book> bookList = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setBookName("c++");
		book1.setPrice(91);
		bookList.add(book1);
		Book book2 = new Book();
		book2.setBookName("python");
		book2.setPrice(62);
		bookList.add(book2);
		try {
			sqlSession.insert("insertForeach", bookList);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
		}
		ConnectionUtil.closeSqlSession(sqlSession);
	}
}
