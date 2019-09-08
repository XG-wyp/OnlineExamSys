package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public Long insert(Manager bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" Insert Into Manager(loginName,loginPwd,nickName,isDisabled) ");
		sb.append(" values(?,?,?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getIsDisabled());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num > 0) {
			sql = " select LAST_INSERT_ID() ";
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				num = result;
			}
		}
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from Manager ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Managerid=? ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(id);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long update(Manager bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Manager set ");
		sb.append(" 	LoginName=? ");
		paramsList.add(bean.getLoginName());
		if (bean.getLoginPwd() != null) {
			sb.append("		,LoginPwd=? ");
			paramsList.add(bean.getLoginPwd());
		}
		if (bean.getNickName() != null) {
			sb.append(" 	,NickName=? ");
			paramsList.add(bean.getNickName());
		}
		sb.append(" 	,isDisabled=? ");
		sb.append(" where Managerid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Manager> list() {
		// TODO Auto-generated method stub
		List<Manager> list = new ArrayList<Manager>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Manager ");
		sb.append(" order by managerid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Manager bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	private Manager toBean(ResultSet rs) throws SQLException {
		Manager bean;
		bean = new Manager();
		bean.setId(rs.getLong("managerid"));
		bean.setLoginName(rs.getString("LoginName"));
		bean.setLoginPwd(rs.getString("LoginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("isdisabled"));
		return bean;
	}

	private Manager toBeanEx(ResultSet rs) throws SQLException {
		Manager bean;
		bean = new Manager();
		bean.setLoginName(rs.getString("LoginName"));
		bean.setLoginPwd(rs.getString("LoginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("isdisabled"));
		return bean;
	}

	@Override
	public Manager load(Long id) {
		// TODO Auto-generated method stub
		Manager bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Manager ");
		sb.append(" where managerid=? ");
		String sql = sb.toString();
		paramsList.add(id);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0l;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from Manager ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Manager loadByName(String name) {
		// TODO Auto-generated method stub
		Manager bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Manager ");
		sb.append(" where loginName=? ");
		String sql = sb.toString();
		paramsList.add(name);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long num = 0l;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from Manager ");
		sb.append(" where loginName=? ");
		String sql = sb.toString();
		paramsList.add(name);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public boolean login(Manager bean) {
		// TODO Auto-generated method stub
		boolean isOK = false;

		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from Manager ");
		sb.append(" where ");
		sb.append(" 	loginName=? ");
		sb.append(" 	and loginPwd=?");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		ResultSet rs = null;
		rs = DbPub.query(conn, sql, params);
		try {
			if (rs.next()) {
				num = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isOK = num > 0;
		return isOK;
	}

	@Override
	public List<Manager> listByName(String name) {
		// TODO Auto-generated method stub
		List<Manager> list = new ArrayList<Manager>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Manager ");
		sb.append(" where LoginName like ? ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		name = "%" + name + "%";
		paramsList.add(name);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			Manager bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	@Override
	public Manager loadByName(String name, Integer type) {
		// TODO Auto-generated method stub
		Manager bean = null;

		String table = "";
		switch (type) {
		case 0:
			table = "manager";
			break;
		case 1:
			table = "teacher";
			break;
		case 2:
			table = "student";
			break;
		}
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from " + table);
		sb.append(" where loginName=? ");
		String sql = sb.toString();
		paramsList.add(name);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

			if (rs.next()) {
				bean = toBeanEx(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return bean;
	}

	@Override
	public List<Manager> listByName(String loginName, String nickName) {
		// TODO Auto-generated method stub
		List<Manager> list = new ArrayList<Manager>();
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Manager ");
		sb.append(" where 1=1 ");
		if (loginName != null && !loginName.isEmpty()) {
			sb.append(" 	and loginName like ? ");
			loginName = "%" + loginName + "%";
			paramsList.add(loginName);
		}
		if (nickName != null && !nickName.isEmpty()) {
			sb.append(" 	and nickName like ? ");
			nickName = "%" + nickName + "%";
			paramsList.add(nickName);
		}
		String sql = sb.toString();

		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			Manager bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

}
