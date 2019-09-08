package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Grade;
import edu.prj.dao.GradeDao;
import edu.prj.dao.impl.GradeDaoImpl;
import edu.prj.service.GradeService;

public class GradeServiceImpl implements GradeService {
GradeDao gradeDao=new GradeDaoImpl();

	@Override
	public Long insert(Grade bean) {
		// TODO Auto-generated method stub
		return gradeDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return gradeDao.delete(id);
	}

	@Override
	public Long update(Grade bean) {
		// TODO Auto-generated method stub
		return gradeDao.update(bean);
	}

	@Override
	public List<Grade> list() {
		// TODO Auto-generated method stub
		return gradeDao.list();
	}

	@Override
	public Grade load(Long id) {
		// TODO Auto-generated method stub
		return gradeDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return gradeDao.count();
	}

	@Override
	public Grade loadByName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.countByName(name);
	}

	@Override
	public List<Grade> listByName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.listByName(name);
	}

	@Override
	public List<Grade> listByName(String GradeName, String CreateBy) {
		// TODO Auto-generated method stub
		return gradeDao.listByName(GradeName, CreateBy);
	}

}
