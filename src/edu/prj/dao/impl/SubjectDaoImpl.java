package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Subject;
import edu.prj.dao.SubjectDao;
import edu.util.DbPub;

public class SubjectDaoImpl implements SubjectDao {

	@Override
	public Long insert(Subject bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" Insert Into Subject(subjectName,status) ");
		sb.append(" values(?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getSubjectName());
		paramsList.add(bean.getStatus());

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
		sb.append(" delete from Subject ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Subjectid=? ");
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
	public Long update(Subject bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Subject set ");
		sb.append(" 	subjectName=? ");
		sb.append("		,status=? ");
		paramsList.add(bean.getSubjectName());
		paramsList.add(bean.getStatus());
		sb.append(" where Subjectid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Subject> list() {
		// TODO Auto-generated method stub
		List<Subject> list = new ArrayList<Subject>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Subject ");
		sb.append(" order by Subjectid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Subject bean = null;
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

	private Subject toBean(ResultSet rs) throws SQLException {
		Subject bean;
		bean = new Subject();
		bean.setId(rs.getLong("Subjectid"));
		bean.setSubjectName(rs.getString("subjectName"));
		bean.setStatus(rs.getString("status"));
		return bean;
	}

	@Override
	public Subject load(Long id) {
		// TODO Auto-generated method stub
		Subject bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Subject ");
		sb.append(" where Subjectid=? ");
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
		sb.append(" select count(1) from Subject ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Subject loadByName(String name) {
		// TODO Auto-generated method stub
		Subject bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Subject ");
		sb.append(" where subjectName=? ");
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
		sb.append(" select count(1) from Subject ");
		sb.append(" where subjectName=? ");
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
	public List<Subject> listByName(String name) {
		// TODO Auto-generated method stub
		List<Subject> list = new ArrayList<Subject>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Subject ");
		sb.append(" where subjectName like ? ");
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
			Subject bean = null;
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
