package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.util.DbPub;

public class TeacherDaoImpl implements TeacherDao {

	@Override
	public Long insert(Teacher bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" Insert Into Teacher(loginName,loginPwd,nickName,isDisabled) ");
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
		sb.append(" delete from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Teacherid=? ");
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
	public Long update(Teacher bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Teacher set ");
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
		sb.append(" where Teacherid=? ");
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
	public List<Teacher> list() {
		// TODO Auto-generated method stub
		List<Teacher> list = new ArrayList<Teacher>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Teacher ");
		sb.append(" order by teacherid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Teacher bean = null;
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

	private Teacher toBean(ResultSet rs) throws SQLException {
		Teacher bean;
		bean = new Teacher();
		bean.setId(rs.getLong("teacherid"));
		bean.setLoginName(rs.getString("LoginName"));
		bean.setLoginPwd(rs.getString("LoginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("isdisabled"));
		return bean;
	}

	private Teacher toBeanEx(ResultSet rs) throws SQLException {
		Teacher bean;
		bean = new Teacher();
		bean.setLoginName(rs.getString("LoginName"));
		bean.setLoginPwd(rs.getString("LoginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("isdisabled"));
		return bean;
	}

	@Override
	public Teacher load(Long id) {
		// TODO Auto-generated method stub
		Teacher bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Teacher ");
		sb.append(" where teacherid=? ");
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
		sb.append(" select count(1) from Teacher ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Teacher loadByName(String name) {
		// TODO Auto-generated method stub
		Teacher bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Teacher ");
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
		sb.append(" select count(1) from Teacher ");
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
	public List<Teacher> listByName(String name) {
		// TODO Auto-generated method stub
		List<Teacher> list = new ArrayList<Teacher>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Teacher ");
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
			Teacher bean = null;
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
	public List<Teacher> listByName(String loginName, String nickName) {
		// TODO Auto-generated method stub
		List<Teacher> list = new ArrayList<Teacher>();
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Teacher ");
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
			Teacher bean = null;
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
	public boolean login(Teacher bean) {
		// TODO Auto-generated method stub
		boolean isOK = false;

		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from Teacher ");
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

}
