/**
 * @description AccountDao接口实现类
 */
package com.spring.daoImpl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.dao.AccountDao;
import com.spring.exception.AccountException;

public class AccountDaoImpl implements AccountDao {
	//setXxx方法注入(setXxx方法必须存在，属性存在与否无关)
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param userName 用户名
	 * @param price 价格
	 * @description 先查询用户账户余额，余额充足则更新（减去书籍价格）
	 */
	@Override
	public void updateAccount(String userName, float price) {
		String sql = "select balance from account where username = ?";
		
		float balance = jdbcTemplate.queryForObject(sql, Float.class, userName);
		//检查余额是否不足，若不足，则抛出异常
		if(balance < price){
			throw new AccountException("余额不足！");
		}
		String sql2 = "update account set balance = balance - ? where username = ?";
		
		jdbcTemplate.update(sql2, new Object[]{price, userName});
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
