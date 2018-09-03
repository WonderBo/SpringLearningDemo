/**
 * @description CashierService接口
 */
package com.spring.service;

import java.util.List;

public interface CashierService {
	public void purchaseOne(String userName, String isbn);
	public void purchaseMany(String userName, List<String> isbns);
}
