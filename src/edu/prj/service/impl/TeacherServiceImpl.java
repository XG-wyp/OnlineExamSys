package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.*;
import edu.prj.dao.*;
import edu.prj.dao.impl.*;
import edu.prj.service.*;

public class TeacherServiceImpl implements TeacherService {
	TeacherDao teacherDao = new TeacherDaoImpl();

	@Override
	public Long insert(Teacher bean) {
		// TODO Auto-generated method stub
		return teacherDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.delete(id);
	}

	@Override
	public Long update(Teacher bean) {
		// TODO Auto-generated method stub
		return teacherDao.update(bean);
	}

	@Override
	public List<Teacher> list() {
		// TODO Auto-generated method stub
		return teacherDao.list();
	}

	@Override
	public Teacher load(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return teacherDao.count();
	}

	@Override
	public Teacher loadByName(String name) {
		// TODO Auto-generated method stub
		return teacherDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return teacherDao.countByName(name);
	}

	@Override
	public List<Teacher> listByName(String name) {
		// TODO Auto-generated method stub
		return teacherDao.listByName(name);
	}

	@Override
	public List<Teacher> listByName(String loginName, String nickName) {
		// TODO Auto-generated method stub
		return teacherDao.listByName(loginName, nickName);
	}

	@Override
	public boolean login(Teacher bean) {
		// TODO Auto-generated method stub
		return teacherDao.login(bean);
	}

}
