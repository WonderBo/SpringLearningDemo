/**
 * @description BookDao接口实现类
 */
package com.spring.daoImpl.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.spring.dao.BookDao;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	/**
	 * @param isbn 书籍编号
	 * @description 根据书籍编号查询书籍价格
	 */
	@Override
	public float findBookPriceByIsbn(String isbn) {
		String hql = "select price from Book where isbn = ?";
		return (float) hibernateTemplate.find(hql, isbn).get(0);
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
