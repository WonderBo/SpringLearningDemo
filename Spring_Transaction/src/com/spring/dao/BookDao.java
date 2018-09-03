/**
 * @description BookDAO接口
 */
package com.spring.dao;

public interface BookDao {
	public float findBookPriceByIsbn(String isbn);
}
