package edu.util;

import java.io.IOException;
import java.sql.*;

public class DbPub {
	public static String STD_DRIVER;
	public static String STD_URL;
	public static String STD_USERNAME;
	public static String STD_PASSWORD;

	public static String filename = "/app.properties";

	static {
		// 从配置文件中获取四大金刚
		java.util.Properties prop = new java.util.Properties();
		try {
			prop.load(DbPub.class.getResourceAsStream(filename));
			STD_DRIVER = prop.getProperty("STD_DRIVER");
			STD_URL = prop.getProperty("STD_URL");
			STD_USERNAME = prop.getProperty("STD_USERNAME");
			STD_PASSWORD = prop.getProperty("STD_PASSWORD");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("【" + filename + "】不存在！");
		}

		// 加载驱动类名
		try {
			Class.forName(DbPub.STD_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("类全名【" + DbPub.STD_DRIVER + "】没有找到。");
		}
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DbPub.STD_URL, DbPub.STD_USERNAME, DbPub.STD_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, PreparedStatement stmt) {
		close(conn, stmt, null);
	}

	public static void close(Connection conn) {
		close(conn, null, null);
	}

	public static ResultSet query(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】时出现错误。" + e.getMessage());
		}
		return rs;
	}

	public static Long queryScalarLong(Connection conn, String sql, Object... params) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long result = 0l;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("执行【" + sql + "】时出现错误。" + e.getMessage());
		}
		return result;
	}

	public static Long update(Connection conn, String sql, Object... params) {
		Integer num = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			num = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return num.longValue();
	}
}
