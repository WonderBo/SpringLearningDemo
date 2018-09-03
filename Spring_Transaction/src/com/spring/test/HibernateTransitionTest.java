/**
 * @description Hibernate + Spring Annotation(注解)配置方式实现事务管理
 */
package com.spring.test;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dao.AccountDao;
import com.spring.dao.BookDao;
import com.spring.dao.BookStockDao;
import com.spring.service.CashierService;

public class HibernateTransitionTest {
	private ApplicationContext applicationContext = null;
	private AccountDao accountDao = null;
	private BookDao bookDao = null;
	private BookStockDao bookStockDao = null;
	private CashierService cashierService = null;
	//非静态代码块在每次调用构造函数的时候都会运行，而静态代码块只在类加载的时候运行一次
	{
		applicationContext = new ClassPathXmlApplicationContext("com/spring/conf/applicationContext_Hibernate.xml");
		accountDao = applicationContext.getBean("accountDao", AccountDao.class);
		bookDao = applicationContext.getBean("bookDao", BookDao.class);
		bookStockDao = applicationContext.getBean("bookStockDao", BookStockDao.class);
		cashierService = applicationContext.getBean("cashierService", CashierService.class);
	}
	
	@Test
	public void testBookDaoFindBookPriceByIsbn(){
		System.out.println(bookDao.findBookPriceByIsbn("1001"));
	}
	
	@Test
	public void testAccountDaoUpdateAccount(){
		accountDao.updateAccount("jack", 100);
	}
	
	@Test
	public void testBookStockDaoUpdateBookStock(){
		bookStockDao.updateBookStock("1001");
	}
	
	@Test
	public void testCashierServicePurchaseOne() {
		cashierService.purchaseOne("jack", "1001");
	}

	@Test
	public void testCashierServicePurchaseMany() {
		cashierService.purchaseMany("jack", Arrays.asList("1001", "1002"));
	}

}
