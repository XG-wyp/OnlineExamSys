package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Subject;
import edu.prj.dao.SubjectDao;
import edu.prj.dao.impl.SubjectDaoImpl;
import edu.prj.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
SubjectDao subjectDao=new SubjectDaoImpl();
	@Override
	public Long insert(Subject bean) {
		// TODO Auto-generated method stub
		return subjectDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return subjectDao.delete(id);
	}

	@Override
	public Long update(Subject bean) {
		// TODO Auto-generated method stub
		return subjectDao.update(bean);
	}

	@Override
	public List<Subject> list() {
		// TODO Auto-generated method stub
		return subjectDao.list();
	}

	@Override
	public Subject load(Long id) {
		// TODO Auto-generated method stub
		return subjectDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return subjectDao.count();
	}

	@Override
	public Subject loadByName(String name) {
		// TODO Auto-generated method stub
		return subjectDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return subjectDao.countByName(name);
	}

	@Override
	public List<Subject> listByName(String name) {
		// TODO Auto-generated method stub
		return subjectDao.listByName(name);
	}

}
