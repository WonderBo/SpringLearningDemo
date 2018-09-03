/**
 * @description 数据库的增删查改操作
 */
package com.cqu.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBServer {

	private DBOperation dbOperation;
	
	public DBServer(String poolName){
		dbOperation = new DBOperation(poolName);
	}
	
	/**
	 * @description 关闭数据库连接
	 */
	public void close(){
		dbOperation.close();
	}
	
	/**
	 * 
	 * @param sql 完整sql语句
	 * @return
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库新增操作
	 */
	public int insert(String sql) throws SQLException{
		return dbOperation.executeUpdate(sql);
	}
	
	/**
	 * 
	 * @param tableName 表名
	 * @param columns 列
	 * @param params 参数
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库新增操作
	 */
	public int insert(String tableName, String columns, HashMap<Integer, Object> params) throws ClassNotFoundException, SQLException{
		String sql = this.createInsertSql(tableName, columns);
		return dbOperation.executeUpdate(sql, params);
	}
	
	/**
	 * 
	 * @param sql 完整sql语句
	 * @return
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库删除操作
	 */
	public int delete(String sql) throws SQLException{
		return dbOperation.executeUpdate(sql);
	}
	
	/**
	 * 
	 * @param tableName 表名
	 * @param condition 条件
	 * @return
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库删除操作
	 */
	public int delete(String tableName, String condition) throws SQLException{
		if(tableName == null){
			return 0;
		}
		String sql = "delete from " + tableName + " where " + condition;
		return dbOperation.executeUpdate(sql);
	}
	
	/**
	 * 
	 * @param sql 完整sql语句
	 * @return
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库更新操作
	 */
	public int update(String sql) throws SQLException{
		return dbOperation.executeUpdate(sql);
	}
	
	/**
	 * 
	 * @param tableName 表名
	 * @param columns 列
	 * @param condition 条件
	 * @param params 参数
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库更新操作
	 */
	public int update(String tableName, String columns, String condition, HashMap<Integer, Object> params) throws ClassNotFoundException, SQLException{
		String sql = this.createUpdateSql(tableName, columns, condition);
		return dbOperation.executeUpdate(sql, params);
	}
	
	/**
	 * 
	 * @param sql 完整sql语句
	 * @return
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库查询操作
	 */
	public ResultSet select(String sql) throws SQLException{
		return dbOperation.executeQuery(sql);
	}
	
	/**
	 * 
	 * @param tableName 表名
	 * @param columns 列
	 * @param condition 条件
	 * @return
	 * @throws SQLException
	 * @author 汪波
	 * @description 数据库查询操作
	 */
	public ResultSet select(String tableName, String columns, String condition) throws SQLException{
		String sql = "select " + columns + " from " + tableName + " where " + condition;
		return dbOperation.executeQuery(sql);
	}
	
	/**
	 * 
	 * @param tableName 表名
	 * @param columns 列
	 * @return sql字符串
	 * @author 汪波
	 * @description 组装 insert sql语句 eg: insert into tableName (column1, column2) values (?,?)
	 */
	public String createInsertSql(String tableName, String columns){
		if(tableName == null || columns == null){
			return "";
		}
		int n = columns.split(",").length;
		StringBuilder sb = new StringBuilder("");
		sb.append("insert into ");
		sb.append(tableName);
		sb.append(" (");
		sb.append(columns);
		sb.append(") values (?");
		for(int i = 1; i < n; i++){
			sb.append(",?");
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 
	 * @param tableName 表名
	 * @param columns 列
	 * @param condition 条件
	 * @return sql字符串
	 * @author 汪波
	 * @description 组装 update sql语句 eg: update tableName set column1=?,column2=? where condition 
	 */
	public String createUpdateSql(String tableName, String columns, String condition){
		if(tableName == null || columns == null){
			return null;
		}
		String[] column = columns.split(",");
		StringBuilder sb = new StringBuilder("");
		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");
		sb.append(column[0]);
		sb.append("=?");
		for(int i = 1; i<column.length; i++){
			sb.append(", ");
			sb.append(column[i]);
			sb.append("=?");
		}
		sb.append(" where ");
		sb.append(condition);
		return sb.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
