/**
 * @description AccountDao接口实现类
 */
package com.spring.daoImpl.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.spring.bean.Account;
import com.spring.dao.AccountDao;
import com.spring.exception.AccountException;

//组件bean定义
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
	//属性byName自动注入(setXxx方法存在与否不影响)，也可以在setXxx方法中进行注入
	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	/**
	 * @param userName 用户名
	 * @param price 价格
	 * @description 先查询用户账户余额，余额充足则更新（减去书籍价格）
	 */
	@Override
	public void updateAccount(String userName, float price) {
		String hql = "select balance from Account where username = ?";
		
		float balance = (float) hibernateTemplate.find(hql, userName).get(0);
		//检查余额是否不足，若不足，则抛出异常
		if(balance < price){
			throw new AccountException("余额不足！");
		}
		//String hql2 = "update Account set balance = balance - ? where userName = ?";
		Account account = hibernateTemplate.get(Account.class, userName);
		account.setBalance(balance - price);
		//HibernateTemplate.update(entity)，并不存在类似于JdbcTemplate.update(sql, new Object[]{args})
		hibernateTemplate.update(account);
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
