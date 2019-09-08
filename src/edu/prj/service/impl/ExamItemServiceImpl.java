package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.ExamItem;
import edu.prj.dao.ExamItemDao;
import edu.prj.dao.impl.ExamItemDaoImpl;
import edu.prj.service.ExamItemService;

public class ExamItemServiceImpl implements ExamItemService{
ExamItemDao examItemDao=new ExamItemDaoImpl();
	@Override
	public Long insert(ExamItem bean) {
		// TODO Auto-generated method stub
		return examItemDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return examItemDao.delete(id);
	}

	@Override
	public Long update(ExamItem bean) {
		// TODO Auto-generated method stub
		return examItemDao.update(bean);
	}

	@Override
	public List<ExamItem> list() {
		// TODO Auto-generated method stub
		return examItemDao.list();
	}

	@Override
	public ExamItem load(Long id) {
		// TODO Auto-generated method stub
		return examItemDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return examItemDao.count();
	}

	@Override
	public ExamItem loadByName(String name) {
		// TODO Auto-generated method stub
		return examItemDao.loadByName(name);
	}

	@Override
	public ExamItem loadById(Long id) {
		// TODO Auto-generated method stub
		return examItemDao.loadById(id);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return examItemDao.countByName(name);
	}

	@Override
	public List<ExamItem> listByName(String name) {
		// TODO Auto-generated method stub
		return examItemDao.listByName(name);
	}

	@Override
	public Long countByExamId(Long ExamId) {
		// TODO Auto-generated method stub
		return examItemDao.countByExamId(ExamId);
	}

	@Override
	public List<ExamItem> listByExamId(Long ExamId) {
		// TODO Auto-generated method stub
		return examItemDao.listByExamId(ExamId);
	}

	@Override
	public Long countNull(Long ExamId) {
		// TODO Auto-generated method stub
		return examItemDao.countNull(ExamId);
	}

	@Override
	public Long countTrue(Long ExamId) {
		// TODO Auto-generated method stub
		return examItemDao.countTrue(ExamId);
	}

}
