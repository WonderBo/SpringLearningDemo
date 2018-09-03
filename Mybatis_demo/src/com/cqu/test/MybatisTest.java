/**
 * @description MyBatis测试
 */
package com.cqu.test;

import org.junit.Test;

import com.cqu.oper.BasicOperation;
import com.cqu.oper.DynamicSQLOperation;
import com.cqu.oper.FurtherOperation;

public class MybatisTest {
	//数据库表基础操作
	BasicOperation basicOperation = new BasicOperation();
	
	//	@Test
	public void testInsertUser() {
		basicOperation.insertUser();
	}

	//	@Test
	public void testDeleteUser() {
		basicOperation.deleteUser();
	}

	//	@Test
	public void testFindById() {
		basicOperation.findById();
	}

	//	@Test
	public void testSelectLogin() {
		basicOperation.selectLogin();
	}

	//	@Test
	public void testSelectLogin2() {
		basicOperation.selectLogin2();
	}
	
	//	@Test
	public void testSelectUser() {
		basicOperation.selectUser();
	}
	
	//	@Test
	public void testSelectUser2() {
		basicOperation.selectUser2();
	}

	//	@Test
	public void testUpdateUser() {
		basicOperation.updateUser();
	}
	
	//	@Test
	public void testTran() {
		basicOperation.tran();
	}
	
	//数据库表高级查询
	FurtherOperation furtherOperation = new FurtherOperation();
	
	//	@Test
	public void testSelectAssociation() {
		furtherOperation.selectAssociation();
	}
	
	//	@Test
	public void testSelectAssociation2() {
		furtherOperation.selectAssociation2();
	}
	
	//	@Test
	public void testSelectAssociation3() {
		furtherOperation.selectAssociation3();
	}
	
	//	@Test
	public void testSelectCollection() {
		furtherOperation.selectCollection();
	}
	
	//	@Test
	public void testSelectDiscriminator() {
		furtherOperation.selectDiscriminator(2);
	}
	
	//动态SQL操作
	DynamicSQLOperation dynamicSQLOperation = new DynamicSQLOperation();
	
	//	@Test
	public void testSelectIf() {
		dynamicSQLOperation.selectIf();
	}
	
	//	@Test
	public void testSelectChoose() {
		dynamicSQLOperation.selectChoose();
	}
	
	//	@Test
	public void testSelectWhere() {
		dynamicSQLOperation.selectWhere();
	}
	
	//	@Test
	public void testUpdateSet() {
		dynamicSQLOperation.updateSet();
	}
	
	//	@Test
	public void testUpdateTrim() {
		dynamicSQLOperation.updateTrim();
	}
	
	@Test
	public void testSelectForeach() {
		dynamicSQLOperation.selectForeach();
	}
	
	@Test
	public void testInsertForeach() {
		dynamicSQLOperation.insertForeach();
	}
}
