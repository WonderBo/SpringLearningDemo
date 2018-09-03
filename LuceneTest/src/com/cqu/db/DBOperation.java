/**
 * 对参数的转化（传入SQL语句）和SQL语句的执行
 */
package com.cqu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBOperation {

	//数据库连接池别名
	private String poolName;
	//数据库连接
	private Connection conn= null;
	
	public DBOperation(String poolName){
		this.poolName = poolName;
	}
	
	/**
	 * @author 汪波
	 * @description 关闭数据库连接
	 */
	public void close(){
		try {
			if(this.conn != null){
				this.conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 汪波
	 * @throws SQLException
	 * @description 打开数据库连接
	 */
	private void open() throws SQLException{
		//先关闭后打开，防止数据库连接溢出
		this.close();
		this.conn = DBManager.getDBManager().getConnection(this.poolName);
	}
	
	/**
	 * 
	 * @param sql 带?（需要传入参数）的sql语句
	 * @param params ?所对应的参数HashMap<参数序号（从1开始），参数值（可能多种类型）>
	 * @return PreparedStatement
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @author 汪波
	 * @description sql语句参数转化
	 */
	private PreparedStatement setPres(String sql, HashMap<Integer, Object> params) throws SQLException, ClassNotFoundException{
		//参数验证
		if(params == null || params.size() < 1){
			return null;
		}
		PreparedStatement pres = this.conn.prepareStatement(sql);
		//依次将HashMap中的参数转化进入PreparedStatement中去
		for(int i = 1; i <= params.size(); i++){
			if(params.get(i) == null){
				pres.setString(i, "");
			}else if(params.get(i).getClass() == Class.forName("java.lang.String")){
				pres.setString(i, params.get(i).toString());
			}else if(params.get(i).getClass() == Class.forName("java.lang.Integer")){
				pres.setInt(i, (Integer) params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Long")){
				pres.setLong(i, (Long)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Double")){
				pres.setDouble(i, (Double)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Float")){
				pres.setFloat(i, (Float)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Boolean")){
				pres.setBoolean(i, (Boolean)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.sql.Date")){
				pres.setDate(i, java.sql.Date.valueOf(params.get(i).toString()));
			}else{
				return null;
			}
		}
		return pres;
	}
	
	/**
	 * 
	 * @param sql 不带传入参数的sql语句
	 * @return 影响行数
	 * @throws SQLException
	 * @author 汪波
	 * @description 执行SQL更新语句，返回影响行数
	 */
	public int executeUpdate(String sql) throws SQLException{
		this.open();
		Statement state = this.conn.createStatement();
		return state.executeUpdate(sql);
	}
	
	/**
	 * 
	 * @param sql 带传入参数的sql语句
	 * @param params 参数HashMap
	 * @return 影响行数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @author 汪波
	 * @description 执行SQL查询语句，返回结果集
	 */
	public int executeUpdate(String sql, HashMap<Integer, Object> params) throws ClassNotFoundException, SQLException{
		this.open();
		PreparedStatement pres = this.setPres(sql, params);
		if(pres == null){
			return 0;
		}
		return pres.executeUpdate();
	}
	
	/**
	 * 
	 * @param sql 不带传入参数的sql语句
	 * @return ResultSet 结果集
	 * @throws SQLException
	 * @author 汪波
	 * @description 执行SQL查询语句，返回结果集
	 */
	public ResultSet executeQuery(String sql) throws SQLException{
		this.open();
		Statement state = this.conn.createStatement();
		return state.executeQuery(sql);
	}
	
	/**
	 * 
	 * @param sql 带传入参数的sql语句
	 * @param params 参数HashMap
	 * @return ResultSet 结果集
	 * @throws SQLException
	 * @author 汪波
	 * @description 执行SQL查询语句，返回结果集
	 */
	public ResultSet executeQuery(String sql, HashMap<Integer, Object> params) throws SQLException, ClassNotFoundException{
		this.open();
		PreparedStatement pres = this.setPres(sql, params);
		if(pres == null){
			return null;
		}
		return pres.executeQuery();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
