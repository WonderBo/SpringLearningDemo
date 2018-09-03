/**
 * @description BookDao接口实现类
 */
package com.spring.daoImpl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.dao.BookDao;

public class BookDaoImpl implements BookDao {
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param isbn 书籍编号
	 * @description 根据书籍编号查询书籍价格
	 */
	@Override
	public float findBookPriceByIsbn(String isbn) {
		String sql = "select price from book where isbn = ?";
		
		return jdbcTemplate.queryForObject(sql, Float.class, isbn);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
