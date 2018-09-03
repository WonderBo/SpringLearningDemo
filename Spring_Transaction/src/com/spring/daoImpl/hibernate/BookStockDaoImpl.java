/**
 * @description BookStockDao接口实现类
 */
package com.spring.daoImpl.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.spring.bean.BookStock;
import com.spring.dao.BookStockDao;
import com.spring.exception.BookStockException;

@Repository("bookStockDao")
public class BookStockDaoImpl implements BookStockDao {
	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	/**
	 * @param isbn 书籍编号
	 * @description 先查询书籍库存，库存充足则更新（减去书籍数量1）
	 */
	@Override
	public void updateBookStock(String isbn) {
		String hql = "select stock from BookStock where isbn = ?";
		
		Integer stock = (Integer) hibernateTemplate.find(hql, isbn).get(0);
		//检查书的库存是否足够，若不够，则抛出异常
		if(stock == 0){
			throw new BookStockException("库存不足！");
		}
		//String hql2 = "update BookStock set stock = stock - 1 where isbn = ?";
		BookStock bookStock = hibernateTemplate.get(BookStock.class, isbn);
		bookStock.setStock(--stock);
		hibernateTemplate.update(bookStock);
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
