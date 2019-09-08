package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Student;
import edu.prj.dao.StudentDao;
import edu.util.DbPub;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Long insert(Student bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" Insert Into Student(loginName,loginPwd,nickName,isDisabled,roomid) ");
		sb.append(" values(?,?,?,?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getNickName());
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getRoomId());
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
		sb.append(" delete from Student ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Studentid=? ");
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
	public Long update(Student bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Student set ");
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
		sb.append(" 	,RoomId=?");
		sb.append(" where Studentid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getRoomId());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Student> list() {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Student ");
		sb.append(" order by studentid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Student bean = null;
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

	private Student toBean(ResultSet rs) throws SQLException {
		Student bean;
		bean = new Student();
		bean.setId(rs.getLong("studentid"));
		bean.setLoginName(rs.getString("LoginName"));
		bean.setLoginPwd(rs.getString("LoginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("isdisabled"));
		bean.setRoomId(rs.getLong("roomid"));
		return bean;
	}

	private Student toBeanEx(ResultSet rs) throws SQLException {
		Student bean;
		bean = new Student();
		bean.setLoginName(rs.getString("LoginName"));
		bean.setLoginPwd(rs.getString("LoginPwd"));
		bean.setNickName(rs.getString("nickName"));
		bean.setIsDisabled(rs.getLong("isdisabled"));
		bean.setRoomId(rs.getLong("roomid"));
		return bean;
	}

	@Override
	public Student load(Long id) {
		// TODO Auto-generated method stub
		Student bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Student ");
		sb.append(" where studentid=? ");
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
		sb.append(" select count(1) from Student ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Student loadByName(String name) {
		// TODO Auto-generated method stub
		Student bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Student ");
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
		sb.append(" select count(1) from Student ");
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
	public List<Student> listByName(String name) {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Student ");
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
			Student bean = null;
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
	public List<Student> listByName(String loginName, String nickName) {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Student ");
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
			Student bean = null;
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
	public boolean login(Student bean) {
		// TODO Auto-generated method stub
		boolean isOK = false;

		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from student ");
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
