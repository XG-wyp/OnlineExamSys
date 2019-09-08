package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Classroom;
import edu.prj.dao.ClassroomDao;
import edu.util.DbPub;

public class ClassroomDaoImpl implements ClassroomDao {

	@Override
	public Long insert(Classroom bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" Insert Into Classroom(RoomName,GradeId) ");
		sb.append(" values(?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getRoomName());
		paramsList.add(bean.getGradeId());
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
		sb.append(" delete from Classroom ");
		sb.append(" where 1=1 ");
		sb.append(" 	and roomid=? ");
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
	public Long update(Classroom bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Classroom set ");
		sb.append(" 	RoomName=? ");
		sb.append("		,GradeId=? ");
		paramsList.add(bean.getRoomName());
		paramsList.add(bean.getGradeId());
		sb.append(" where roomid=? ");
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
	public List<Classroom> list() {
		// TODO Auto-generated method stub
		List<Classroom> list = new ArrayList<Classroom>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select C.*,G.GradeName as GradeName  ");
		sb.append(" from Classroom C ");
		sb.append(" 	left join Grade G on C.GradeId=G.gradeid ");
		sb.append(" order by roomid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Classroom bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	private Classroom toBean(ResultSet rs) throws SQLException {
		Classroom bean;
		bean = new Classroom();
		bean.setId(rs.getLong("roomid"));
		bean.setRoomName(rs.getString("roomName"));
		bean.setGradeId(rs.getLong("gradeid"));
		return bean;
	}

	private Classroom toBeanEx(ResultSet rs) throws SQLException {
		Classroom bean;
		bean = new Classroom();
		bean.setId(rs.getLong("roomid"));
		bean.setRoomName(rs.getString("roomName"));
		bean.setGradeId(rs.getLong("gradeid"));
		bean.setGradeName(rs.getString("gradeName"));
		return bean;
	}

	@Override
	public Classroom load(Long id) {
		// TODO Auto-generated method stub
		Classroom bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Classroom ");
		sb.append(" where roomid=? ");
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
		sb.append(" select count(1) from Classroom ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Classroom loadByName(String name) {
		// TODO Auto-generated method stub
		Classroom bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Classroom ");
		sb.append(" where RoomName=? ");
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
		sb.append(" select count(1) from Classroom ");
		sb.append(" where roomName=? ");
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
	public List<Classroom> listByName(String name) {
		// TODO Auto-generated method stub
		List<Classroom> list = new ArrayList<Classroom>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select C.*,G.GradeName as GradeName  ");
		sb.append(" from Classroom C ");
		sb.append(" 	left join Grade G on C.GradeId=G.gradeid ");
		sb.append(" where 1=1 ");
		sb.append(" 	and roomName like ? ");
		sb.append(" order by roomid ");
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
			Classroom bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);
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
	public List<Classroom> listByName(String roomName, String gradeName) {
		// TODO Auto-generated method stub
		List<Classroom> list = new ArrayList<Classroom>();
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select C.*,G.GradeName as GradeName  ");
		sb.append(" from Classroom C ");
		sb.append(" 	left join Grade G on C.GradeId=G.gradeid ");
		sb.append(" where 1=1 ");
		if (roomName != null && !roomName.isEmpty()) {
			sb.append(" 	and roomName like ? ");
			roomName = "%" + roomName + "%";
			paramsList.add(roomName);
		}
		if (gradeName != null && !gradeName.isEmpty()) {
			sb.append(" 	and gradeName like ? ");
			gradeName = "%" + gradeName + "%";
			paramsList.add(gradeName);
		}
		sb.append(" order by roomid ");
		String sql = sb.toString();

		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			Classroom bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);
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
