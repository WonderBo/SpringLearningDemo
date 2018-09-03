/**
 * @description BookStockDao接口实现类
 */
package com.spring.daoImpl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.dao.BookStockDao;
import com.spring.exception.BookStockException;

public class BookStockDaoImpl implements BookStockDao {
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param isbn 书籍编号
	 * @description 先查询书籍库存，库存充足则更新（减去书籍数量1）
	 */
	@Override
	public void updateBookStock(String isbn) {
		String sql = "select stock from bookstock where isbn = ?";
		
		Integer stock = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		//检查书的库存是否足够，若不够，则抛出异常
		if(stock == 0){
			throw new BookStockException("库存不足！");
		}
		String sql2 = "update bookstock set stock = stock - 1 where isbn = ?";
		
		jdbcTemplate.update(sql2, isbn);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
