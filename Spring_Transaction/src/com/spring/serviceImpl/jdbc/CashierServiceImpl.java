/**
 * @description CashierService实现类
 */
package com.spring.serviceImpl.jdbc;

import java.util.List;

import com.spring.dao.AccountDao;
import com.spring.dao.BookDao;
import com.spring.dao.BookStockDao;
import com.spring.service.CashierService;

public class CashierServiceImpl implements CashierService {
	private AccountDao accountDao;
	private BookDao bookDao;
	private BookStockDao bookStockDao;
	
	/**
	 * @param userName 用户名
	 * @param isbn 书籍编号
	 * @description 购买单本书籍业务逻辑
	 */
	@Override
	public void purchaseOne(String userName, String isbn) {
		//1.获取书的单价
		float price = bookDao.findBookPriceByIsbn(isbn);
		//2.更新书的库存
		bookStockDao.updateBookStock(isbn);
		//3.更新用户余额
		accountDao.updateAccount(userName, price);
	}

	/**
	 * @param userName 用户名
	 * @param isbns 书籍编号列表
	 * @description 购买多本书籍业务逻辑（两个purchase业务方法相互调用主要用于测试了事务的传播行为）
	 */
	@Override
	public void purchaseMany(String userName, List<String> isbns) {
		for(String isbn : isbns){
			purchaseOne(userName, isbn);
		}
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public BookStockDao getBookStockDao() {
		return bookStockDao;
	}

	public void setBookStockDao(BookStockDao bookStockDao) {
		this.bookStockDao = bookStockDao;
	}

}
