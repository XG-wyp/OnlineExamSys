package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.ExamItem;
import edu.prj.bean.ExamItem;
import edu.prj.dao.ExamItemDao;
import edu.util.DbPub;

public class ExamItemDaoImpl implements ExamItemDao {

	@Override
	public Long insert(ExamItem bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into ExamItem(Examid,questionId,stuAnswer,stdAnswer,stdScore,markResult,gainScore) ");
		sb.append(" values(?,?,?,?,?,?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getExamId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getStuAnswer());
		paramsList.add(bean.getStdAnswer());
		paramsList.add(bean.getStdScore());
		paramsList.add(bean.getMarkResult());
		paramsList.add(bean.getGainScore());
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
		sb.append(" delete from ExamItem ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Itemid=? ");
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
	public Long update(ExamItem bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update ExamItem set ");
		sb.append(" 	ExamId=? ");
		sb.append(" 	,questionId=? ");
		sb.append("		,stuAnswer=? ");
		sb.append("		,stdAnswer=? ");
		sb.append("		,stdScore=? ");
		sb.append("		,markResult=? ");
		sb.append("		,gainScore=? ");
		sb.append(" where Itemid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getExamId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getStuAnswer());
		paramsList.add(bean.getStdAnswer());
		paramsList.add(bean.getStdScore());
		paramsList.add(bean.getMarkResult());
		paramsList.add(bean.getGainScore());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<ExamItem> list() {
		// TODO Auto-generated method stub
		List<ExamItem> list = new ArrayList<ExamItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ExamItem ");
		sb.append(" order by Itemid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			ExamItem bean = null;
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

	private ExamItem toBean(ResultSet rs) throws SQLException {
		ExamItem bean;
		bean = new ExamItem();
		bean.setId(rs.getLong("Itemid"));
		bean.setQuestionId(rs.getLong("questionId"));
		bean.setExamId(rs.getLong("examId"));
		bean.setStuAnswer(rs.getString("stuAnswer"));
		bean.setStdAnswer(rs.getString("stdAnswer"));
		bean.setStdScore(rs.getLong("stdScore"));
		bean.setMarkResult(rs.getLong("markResult"));
		bean.setGainScore(rs.getLong("gainScore"));
		return bean;
	}

	@Override
	public ExamItem load(Long id) {
		// TODO Auto-generated method stub
		ExamItem bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ExamItem ");
		sb.append(" where Itemid=? ");
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
		sb.append(" select count(1) from ExamItem ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public ExamItem loadByName(String name) {
		// TODO Auto-generated method stub
		ExamItem bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ExamItem ");
		sb.append(" where examItemname=? ");
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
		sb.append(" select count(1) from ExamItem ");
		sb.append(" where examItemName=? ");
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
	public List<ExamItem> listByName(String name) {
		// TODO Auto-generated method stub
		List<ExamItem> list = new ArrayList<ExamItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select P.*,S.subjectName as subjectName  ");
		sb.append(" from ExamItem P ");
		sb.append(" 	left join Subject S on P.subjectId=S.subjectId ");
		sb.append(" where ExamItemName like ? ");
		sb.append(" order by Itemid ");
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
			ExamItem bean = null;
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
	public ExamItem loadById(Long id) {
		// TODO Auto-generated method stub
		ExamItem bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select E.*,S.NickName as studentName,P.paperName as paperName,B.SubjectName as SubjectName ");
		sb.append(" from ExamItem E ");
		sb.append(" 	left join Student S on E.studentId=S.studentId ");
		sb.append(" 	left join Paper P on E.PaperId=P.PaperId ");
		sb.append(" 	left join Subject B on E.SubjectId=B.SubjectId ");
		sb.append(" where Itemid=? ");
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
	public Long countByExamId(Long ExamId) {
		// TODO Auto-generated method stub
		Long num = 0l;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from ExamItem ");
		sb.append(" where 1=1 ");
		sb.append(" 	and ExamId=? ");
		paramsList.add(ExamId);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<ExamItem> listByExamId(Long ExamId) {
		// TODO Auto-generated method stub
		List<ExamItem> list = new ArrayList<ExamItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ExamItem");
		sb.append(" where examid=? ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(ExamId);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			ExamItem bean = null;
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
	public Long countNull(Long ExamId) {
		// TODO Auto-generated method stub
		Long num = 0l;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from ExamItem ");
		sb.append(" where 1=1 ");
		sb.append(" 	and ExamId=? ");
		sb.append(" 	and (markResult=1 ");
		sb.append(" 	or markResult=0) ");
		paramsList.add(ExamId);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long countTrue(Long ExamId) {
		// TODO Auto-generated method stub
		Long num = 0l;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from ExamItem ");
		sb.append(" where 1=1 ");
		sb.append(" 	and ExamId=? ");
		sb.append(" 	and markResult=1 ");
		paramsList.add(ExamId);
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

}
