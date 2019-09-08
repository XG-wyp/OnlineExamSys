package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.PaperItem;
import edu.prj.dao.PaperItemDao;
import edu.prj.dao.impl.PaperItemDaoImpl;
import edu.prj.service.PaperItemService;

public class PaperItemServiceImpl implements PaperItemService {
	PaperItemDao paperItemDao = new PaperItemDaoImpl();

	@Override
	public Long insert(PaperItem bean) {
		// TODO Auto-generated method stub
		return paperItemDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return paperItemDao.delete(id);
	}

	@Override
	public Long update(PaperItem bean) {
		// TODO Auto-generated method stub
		return paperItemDao.update(bean);
	}

	@Override
	public List<PaperItem> list() {
		// TODO Auto-generated method stub
		return paperItemDao.list();
	}

	@Override
	public PaperItem load(Long id) {
		// TODO Auto-generated method stub
		return paperItemDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return paperItemDao.count();
	}

	@Override
	public PaperItem loadByName(String name) {
		// TODO Auto-generated method stub
		return paperItemDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return paperItemDao.countByName(name);
	}

	@Override
	public List<PaperItem> listByName(String name) {
		// TODO Auto-generated method stub
		return paperItemDao.listByName(name);
	}

	@Override
	public List<PaperItem> listByPaperId(Long paperId) {
		// TODO Auto-generated method stub
		return paperItemDao.listByPaperId(paperId);
	}

}
