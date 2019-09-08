package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.Question;
import edu.prj.bean.Question;
import edu.prj.dao.QuestionDao;
import edu.util.DbPub;

public class QuestionDaoImpl implements QuestionDao {

	@Override
	public Long insert(Question bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		sb.append(" Insert Into Question(Qtype,question,ItemA,ItemB,ItemC,ItemD,ItemE,ItemF,answer,subjectId,tag) ");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?) ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(bean.getqType());
		paramsList.add(bean.getQuestion());
		paramsList.add(bean.getItemA());
		paramsList.add(bean.getItemB());
		paramsList.add(bean.getItemC());
		paramsList.add(bean.getItemD());
		paramsList.add(bean.getItemE());
		paramsList.add(bean.getItemF());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getSubjectID());
		paramsList.add(bean.getTag());
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
		sb.append(" delete from Question ");
		sb.append(" where 1=1 ");
		sb.append(" 	and Questionid=? ");
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
	public Long update(Question bean) {
		// TODO Auto-generated method stub
		Long num = 0l;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Question set ");
		sb.append(" 	Qtype=? ");
		sb.append(" 	,question=? ");
		sb.append("		,ItemA=? ");
		sb.append("		,ItemB=? ");
		sb.append("		,ItemC=? ");
		sb.append("		,ItemD=? ");
		sb.append("		,ItemE=? ");
		sb.append("		,ItemF=? ");
		sb.append("		,answer=? ");
		sb.append("		,subjectID=? ");
		sb.append("		,tag=? ");
		sb.append(" where Questionid=? ");
		String sql = sb.toString();
		paramsList.add(bean.getqType());
		paramsList.add(bean.getQuestion());
		paramsList.add(bean.getItemA());
		paramsList.add(bean.getItemB());
		paramsList.add(bean.getItemC());
		paramsList.add(bean.getItemD());
		paramsList.add(bean.getItemE());
		paramsList.add(bean.getItemF());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getSubjectID());
		paramsList.add(bean.getTag());
		paramsList.add(bean.getId());
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Question> list() {
		// TODO Auto-generated method stub
		List<Question> list = new ArrayList<Question>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Question ");
		sb.append(" order by Questionid ");
		String sql = sb.toString();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql);

		try {
			Question bean = null;
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

	private Question toBean(ResultSet rs) throws SQLException {
		Question bean;
		bean = new Question();
		bean.setId(rs.getLong("Questionid"));
		bean.setqType(rs.getLong("qType"));
		bean.setQuestion(rs.getString("question"));
		bean.setItemA(rs.getString("ItemA"));
		bean.setItemB(rs.getString("ItemB"));
		bean.setItemC(rs.getString("ItemC"));
		bean.setItemD(rs.getString("ItemD"));
		bean.setItemE(rs.getString("ItemE"));
		bean.setItemF(rs.getString("ItemF"));
		bean.setAnswer(rs.getString("answer"));
		bean.setSubjectID(rs.getLong("subjectID"));
		bean.setTag(rs.getString("tag"));
		return bean;
	}

	private Question toBeanEx(ResultSet rs) throws SQLException {
		Question bean;
		bean = new Question();
		bean.setId(rs.getLong("Questionid"));
		bean.setqType(rs.getLong("qType"));
		bean.setQuestion(rs.getString("question"));
		bean.setItemA(rs.getString("ItemA"));
		bean.setItemB(rs.getString("ItemB"));
		bean.setItemC(rs.getString("ItemC"));
		bean.setItemD(rs.getString("ItemD"));
		bean.setItemE(rs.getString("ItemE"));
		bean.setItemF(rs.getString("ItemF"));
		bean.setAnswer(rs.getString("answer"));
		bean.setSubjectID(rs.getLong("subjectID"));
		bean.setSubject(rs.getString("subjectName"));
		bean.setTag(rs.getString("tag"));
		return bean;
	}

	@Override
	public Question load(Long id) {
		// TODO Auto-generated method stub
		Question bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Question ");
		sb.append(" where Questionid=? ");
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
		sb.append(" select count(1) from Question ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Question loadByName(String name) {
		// TODO Auto-generated method stub
		Question bean = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from Question ");
		sb.append(" where question=? ");
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
		sb.append(" select count(1) from Question ");
		sb.append(" where question=? ");
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
	public List<Question> listByName(String name) {
		// TODO Auto-generated method stub
		List<Question> list = new ArrayList<Question>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select Q.*,S.subjectName as subjectName  ");
		sb.append(" from Question Q ");
		sb.append(" 	left join Subject S on Q.subjectId=S.subjectId ");
		sb.append(" where question like ? ");
		sb.append(" order by questionID ");
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
			Question bean = null;
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
	public Long countBySubjectId(Long Id) {
		// TODO Auto-generated method stub
		Long num = 0l;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(1) from Question ");
		sb.append(" where subjectId=? ");
		String sql = sb.toString();
		paramsList.add(Id);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public List<Question> listBySubjectId(Long subjectId) {
		// TODO Auto-generated method stub
		List<Question> list = new ArrayList<Question>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select Q.*,S.subjectName as subjectName  ");
		sb.append(" from Question Q ");
		sb.append(" 	left join Subject S on Q.subjectId=S.subjectId ");
		sb.append(" where Q.subjectId= ? ");
		sb.append(" order by questionID ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(subjectId);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			Question bean = null;
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
	public List<Question> list(String questionName, String subjectName) {
		// TODO Auto-generated method stub
		List<Question> list = new ArrayList<Question>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select Q.*,S.subjectName as subjectName  ");
		sb.append(" from Question Q ");
		sb.append(" 	left join Subject S on Q.subjectId=S.subjectId ");
		sb.append(" where 1=1 ");
		sb.append(" 	and question like ? ");
		sb.append(" 	and subjectName like ? ");
		sb.append(" order by questionID ");
		String sql = sb.toString();
		List<Object> paramsList = new ArrayList<Object>();
		questionName = "%" + questionName + "%";
		subjectName = "%" + subjectName + "%";
		paramsList.add(questionName);
		paramsList.add(subjectName);
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			Question bean = null;
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
