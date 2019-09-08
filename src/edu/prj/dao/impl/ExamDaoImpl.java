package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Exam;
import edu.prj.bean.Exam;
import edu.prj.dao.ExamDao;
import edu.util.DbPub;

public class ExamDaoImpl implements ExamDao {

	@Override
	public Long insert(Exam bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into Exam(examId,studentId,PaperId,subjectId,StartOn,EndOn) ");
		sb.append(" values(?,?,?,?,?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getId());
		paramsList.add(bean.getStudentId());
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getSubjectId());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
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
		sb.append(" delete from Exam ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Examid=? ");
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
	public Long update(Exam bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Exam set ");
		sb.append(" 	studentId=? ");
		sb.append("		,paperId=? ");
		sb.append("		,subjectId=? ");
		sb.append("		,startOn=? ");
		sb.append("		,endOn=? ");
		sb.append("		,totalScore=? ");
		sb.append(" where Examid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getStudentId());
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getSubjectId());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Exam> list() {
		// TODO Auto-generated method stub
		List<Exam> list = new ArrayList<Exam>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Exam ");
		sb.append(" order by Examid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Exam bean = null;
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

	private Exam toBean(ResultSet rs) throws SQLException {
		Exam bean;
		bean = new Exam();
		bean.setId(rs.getLong("Examid"));
		bean.setStudentId(rs.getLong("studentId"));
		bean.setPaperId(rs.getLong("paperId"));
		bean.setTotalScore(rs.getLong("totalScore"));
		bean.setStartOn(rs.getDate("startOn"));
		bean.setEndOn(rs.getDate("endOn"));
		bean.setSubjectId(rs.getLong("subjectId"));
		return bean;
	}

	private Exam toBeanEx(ResultSet rs) throws SQLException {
		Exam bean;
		bean = new Exam();
		bean.setId(rs.getLong("Examid"));
		bean.setStudentId(rs.getLong("studentId"));
		bean.setPaperId(rs.getLong("paperId"));
		bean.setSubjectId(rs.getLong("subjectId"));
		bean.setTotalScore(rs.getLong("totalScore"));
		bean.setStartOn(rs.getDate("startOn"));
		bean.setEndOn(rs.getDate("endOn"));
		bean.setStudentName(rs.getString("studentName"));
		bean.setPaperName(rs.getString("paperName"));
		bean.setSubjectName(rs.getString("subjectName"));
		return bean;
	}

	@Override
	public Exam load(Long id, Long stuId) {
		// TODO Auto-generated method stub
		Exam bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Exam ");
		sb.append(" where paperid=? ");
		sb.append(" 	and studentId=?");
		String sql = sb.toString();
		paramsList.add(id);
		paramsList.add(stuId);
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
		sb.append(" select count(1) from Exam ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Exam loadByName(String name) {
		// TODO Auto-generated method stub
		Exam bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Exam ");
		sb.append(" where examname=? ");
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
		sb.append(" select count(1) from Exam ");
		sb.append(" where examName=? ");
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
	public List<Exam> listByName(String name) {
		// TODO Auto-generated method stub
		List<Exam> list = new ArrayList<Exam>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select P.*,S.subjectName as subjectName  ");
		sb.append(" from Exam P ");
		sb.append(" 	left join Subject S on P.subjectId=S.subjectId ");
		sb.append(" where ExamName like ? ");
		sb.append(" order by examID ");
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
			Exam bean = null;
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
	public Exam loadById(Long id) {
		// TODO Auto-generated method stub
		Exam bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select E.*,S.NickName as studentName,P.paperName as paperName,B.SubjectName as SubjectName ");
		sb.append(" from Exam E ");
		sb.append(" 	left join Student S on E.studentId=S.studentId ");
		sb.append(" 	left join Paper P on E.PaperId=P.PaperId ");
		sb.append(" 	left join Subject B on E.SubjectId=B.SubjectId ");
		sb.append(" where examId=? ");
		String sql = sb.toString();
		paramsList.add(id);
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
	public Exam load(Long id) {
		// TODO Auto-generated method stub
		Exam bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Exam ");
		sb.append(" where Examid=? ");
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
	public List<Exam> listByStuId(Long stuId) {
		// TODO Auto-generated method stub
		List<Exam> list = new ArrayList<Exam>();
		Exam bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select E.*,S.NickName as studentName,P.paperName as paperName,B.SubjectName as SubjectName ");
		sb.append(" from Exam E ");
		sb.append(" 	left join Student S on E.studentId=S.studentId ");
		sb.append(" 	left join Paper P on E.PaperId=P.PaperId ");
		sb.append(" 	left join Subject B on E.SubjectId=B.SubjectId ");
		sb.append(" where E.studentId=? ");
		String sql = sb.toString();
		paramsList.add(stuId);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

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
	public List<Exam> listByName(String subjectName, String nickName) {
		// TODO Auto-generated method stub
		List<Exam> list = new ArrayList<Exam>();
		Exam bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select E.*,S.NickName as studentName,P.paperName as paperName,B.SubjectName as SubjectName ");
		sb.append(" from Exam E ");
		sb.append(" 	left join Student S on E.studentId=S.studentId ");
		sb.append(" 	left join Paper P on E.PaperId=P.PaperId ");
		sb.append(" 	left join Subject B on E.SubjectId=B.SubjectId ");
		sb.append(" where subjectName like ? ");
		sb.append(" 	and S.nickName like ? ");
		String sql = sb.toString();
		subjectName = "%" + subjectName + "%";
		nickName = "%" + nickName + "%";
		paramsList.add(subjectName);
		paramsList.add(nickName);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {

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
