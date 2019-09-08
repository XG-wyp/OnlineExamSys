package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.Paper;
import edu.prj.bean.Paper;
import edu.prj.dao.PaperDao;
import edu.util.DbPub;

public class PaperDaoImpl implements PaperDao {

	@Override
	public Long insert(Paper bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into Paper(paperName,totalScore,perScore,QuestionNum,examMinute,startOn,endOn,subjectId) ");
		sb.append(" values(?,?,?,?,?,?,?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getPaperName());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getPerScore());
		paramsList.add(bean.getQuestionNum());
		paramsList.add(bean.getExamMinute());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getSubjectId());
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
		sb.append(" delete from Paper ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Paperid=? ");
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
	public Long update(Paper bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Paper set ");
		sb.append(" 	paperName=? ");
		sb.append(" 	,totalScore=? ");
		sb.append("		,perScore=? ");
		sb.append("		,questionNum=? ");
		sb.append("		,examMinute=? ");
		sb.append("		,startOn=? ");
		sb.append("		,endOn=? ");
		sb.append("		,hasGenerate=? ");
		sb.append("		,subjectId=? ");
		sb.append(" where Paperid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getPaperName());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getPerScore());
		paramsList.add(bean.getQuestionNum());
		paramsList.add(bean.getExamMinute());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getHasGenerate());
		paramsList.add(bean.getSubjectId());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Paper> list() {
		// TODO Auto-generated method stub
		List<Paper> list = new ArrayList<Paper>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Paper ");
		sb.append(" order by Paperid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Paper bean = null;
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

	private Paper toBean(ResultSet rs) throws SQLException {
		Paper bean;
		bean = new Paper();
		bean.setId(rs.getLong("Paperid"));
		bean.setPaperName(rs.getString("paperName"));
		bean.setTotalScore(rs.getLong("totalScore"));
		bean.setPerScore(rs.getLong("perScore"));
		bean.setQuestionNum(rs.getLong("questionNum"));
		bean.setExamMinute(rs.getLong("examMinute"));
		bean.setStartOn(rs.getDate("startOn"));
		bean.setEndOn(rs.getDate("endOn"));
		bean.setHasGenerate(rs.getLong("hasGenerate"));
		bean.setSubjectId(rs.getLong("subjectId"));
		return bean;
	}

	private Paper toBeanEx(ResultSet rs) throws SQLException {
		Paper bean;
		bean = new Paper();
		bean.setId(rs.getLong("Paperid"));
		bean.setPaperName(rs.getString("paperName"));
		bean.setTotalScore(rs.getLong("totalScore"));
		bean.setPerScore(rs.getLong("perScore"));
		bean.setQuestionNum(rs.getLong("questionNum"));
		bean.setExamMinute(rs.getLong("examMinute"));
		bean.setStartOn(rs.getDate("startOn"));
		bean.setEndOn(rs.getDate("endOn"));
		bean.setHasGenerate(rs.getLong("hasGenerate"));
		bean.setSubjectId(rs.getLong("subjectId"));
		bean.setSubjectName(rs.getString("subjectName"));
		return bean;
	}

	@Override
	public Paper load(Long id) {
		// TODO Auto-generated method stub
		Paper bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Paper ");
		sb.append(" where Paperid=? ");
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
		sb.append(" select count(1) from Paper ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Paper loadByName(String name) {
		// TODO Auto-generated method stub
		Paper bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Paper ");
		sb.append(" where papername=? ");
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
		sb.append(" select count(1) from Paper ");
		sb.append(" where paperName=? ");
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
	public List<Paper> listByName(String name) {
		// TODO Auto-generated method stub
		List<Paper> list = new ArrayList<Paper>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select P.*,S.subjectName as subjectName  ");
		sb.append(" from Paper P ");
		sb.append(" 	left join Subject S on P.subjectId=S.subjectId ");
		sb.append(" where PaperName like ? ");
		sb.append(" order by paperID ");
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
			Paper bean = null;
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
	public Paper loadById(Long id) {
		// TODO Auto-generated method stub
		Paper bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Paper ");
		sb.append(" where paperId=? ");
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
	public List<Paper> listForStudent(String name) {
		// TODO Auto-generated method stub
		List<Paper> list = new ArrayList<Paper>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select P.*,S.subjectName as subjectName  ");
		sb.append(" from Paper P ");
		sb.append(" 	left join Subject S on P.subjectId=S.subjectId ");
		sb.append(" where PaperName like ? ");
		sb.append(" 	and hasGenerate=1 ");
		sb.append(" order by paperID ");
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
			Paper bean = null;
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
	public Paper loadBy(Long id) {
		// TODO Auto-generated method stub
		Paper bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select P.*,S.subjectName as subjectName  ");
		sb.append(" from Paper P ");
		sb.append(" 	left join Subject S on P.subjectId=S.subjectId ");
		sb.append(" where PaperId = ? ");
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

}
