package edu.prj.dao.impl;

import java.util.*;

import edu.prj.bean.Grade;
import edu.prj.bean.Student;
import edu.prj.dao.GradeDao;
import edu.util.DbPub;

import java.sql.*;

public class GradeDaoImpl implements GradeDao {

	@Override
	public Long insert(Grade bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into Grade(GradeName,CreateOn,createBy) ");
		sb.append(" values(?,?,?) ");
		String sql = sb.toString();
		paramsList.add(bean.getGradeName());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getCreateBy());
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
		sb.append(" delete from Grade ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Gradeid=? ");
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
	public Long update(Grade bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Grade set ");
		sb.append(" 	GradeName=? ");
		sb.append("		,UpdateOn=? ");
		sb.append(" 	,UpdateBy=? ");
		sb.append(" where Gradeid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getGradeName());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Grade> list() {
		// TODO Auto-generated method stub
		List<Grade> list = new ArrayList<Grade>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Grade ");
		sb.append(" order by gradeid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Grade bean = null;
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

	private Grade toBean(ResultSet rs) throws SQLException {
		Grade bean;
		bean = new Grade();
		bean.setId(rs.getLong("gradeid"));
		bean.setGradeName(rs.getString("GradeName"));
		bean.setCreateOn(rs.getDate("CreateOn"));
		bean.setCreateBy(rs.getString("createBy"));
		bean.setUpdateOn(rs.getDate("UpdateOn"));
		bean.setUpdateBy(rs.getString("UpdateBy"));
		return bean;
	}

	

	@Override
	public Grade load(Long id) {
		// TODO Auto-generated method stub
		Grade bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Grade ");
		sb.append(" where gradeid=? ");
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
		sb.append(" select count(1) from Grade ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Grade loadByName(String name) {
		// TODO Auto-generated method stub
		Grade bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Grade ");
		sb.append(" where GradeName=? ");
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
		sb.append(" select count(1) from Grade ");
		sb.append(" where GradeName=? ");
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
	public List<Grade> listByName(String name) {
		// TODO Auto-generated method stub
		List<Grade> list = new ArrayList<Grade>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Grade ");
		sb.append(" where GradeName like ? ");
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
			Grade bean = null;
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
	public List<Grade> listByName(String GradeName, String CreateBy) {
		// TODO Auto-generated method stub
		List<Grade> list = new ArrayList<Grade>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Grade ");
		sb.append(" where 1=1 ");
		if(GradeName!=null&&!GradeName.isEmpty()) {
			sb.append(" and GradeName like ? ");
			GradeName = "%" + GradeName + "%";
			paramsList.add(GradeName);
		}
		if(CreateBy!=null&&!CreateBy.isEmpty()) {
			sb.append(" and CreateBy like ? ");
			CreateBy = "%" + CreateBy + "%";
			paramsList.add(CreateBy);
		}
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			Grade bean = null;
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
