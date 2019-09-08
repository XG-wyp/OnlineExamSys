package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.PaperItem;
import edu.prj.bean.PaperItem;
import edu.prj.dao.PaperItemDao;
import edu.util.DbPub;

public class PaperItemDaoImpl implements PaperItemDao {

	@Override
	public Long insert(PaperItem bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into PaperItem(paperId,questionId,answer,score) ");
		sb.append(" values(?,?,?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getScore());
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
		sb.append(" delete from PaperItem ");
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
	public Long update(PaperItem bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update PaperItem set ");
		sb.append(" 	paperId=? ");
		sb.append(" 	,qusetionId=? ");
		sb.append("		,answer=? ");
		sb.append("		,score=? ");
		sb.append(" where Itemid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getPaperId());
		paramsList.add(bean.getQuestionId());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getScore());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<PaperItem> list() {
		// TODO Auto-generated method stub
		List<PaperItem> list = new ArrayList<PaperItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from PaperItem ");
		sb.append(" order by Itemid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			PaperItem bean = null;
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

	private PaperItem toBean(ResultSet rs) throws SQLException {
		PaperItem bean;
		bean = new PaperItem();
		bean.setId(rs.getLong("Itemid"));
		bean.setPaperId(rs.getLong("paperId"));
		bean.setQuestionId(rs.getLong("questionId"));
		bean.setAnswer(rs.getString("answer"));
		bean.setScore(rs.getLong("score"));
		return bean;
	}



	@Override
	public PaperItem load(Long id) {
		// TODO Auto-generated method stub
		PaperItem bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from PaperItem ");
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
		sb.append(" select count(1) from PaperItem ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public PaperItem loadByName(String name) {
		// TODO Auto-generated method stub
		PaperItem bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from PaperItem ");
		sb.append(" where paperItemname=? ");
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
		sb.append(" select count(1) from PaperItem ");
		sb.append(" where paperItemName=? ");
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
	public List<PaperItem> listByName(String name) {
		// TODO Auto-generated method stub
		List<PaperItem> list = new ArrayList<PaperItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from paperItem ");
		sb.append(" where PaperItemName like ? ");
		sb.append(" order by ItemID ");
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
			PaperItem bean = null;
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
	public List<PaperItem> listByPaperId(Long paperId) {
		// TODO Auto-generated method stub
		List<PaperItem> list = new ArrayList<PaperItem>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from paperItem ");
		sb.append(" where paperId = ? ");
		sb.append(" order by ItemID ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(paperId);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			PaperItem bean = null;
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
