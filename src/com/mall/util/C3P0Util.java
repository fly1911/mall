package com.mall.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	//得到一连接数据源
	static DataSource ds = new ComboPooledDataSource();
	
	public static DataSource getDataSource(){
		return ds;
	}
	//得到连接的方法
	public static Connection getConn() throws SQLException{
		return ds.getConnection();
	}
	//关闭资源
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs= null;
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt= null;
		}
		if(conn!=null){
			try {
				conn.close();//放心的关。是否关闭取决于conn是从哪里来的。
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn= null;
		}
	}
}
