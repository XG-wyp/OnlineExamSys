package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.Classroom;
import edu.prj.dao.ClassroomDao;
import edu.prj.dao.impl.ClassroomDaoImpl;
import edu.prj.service.ClassroomService;

public class ClassroomServiceImpl implements ClassroomService {
	ClassroomDao classroomDao = new ClassroomDaoImpl();

	@Override
	public Long insert(Classroom bean) {
		// TODO Auto-generated method stub
		return classroomDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return classroomDao.delete(id);
	}

	@Override
	public Long update(Classroom bean) {
		// TODO Auto-generated method stub
		return classroomDao.update(bean);
	}

	@Override
	public List<Classroom> list() {
		// TODO Auto-generated method stub
		return classroomDao.list();
	}

	@Override
	public Classroom load(Long id) {
		// TODO Auto-generated method stub
		return classroomDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return classroomDao.count();
	}

	@Override
	public Classroom loadByName(String name) {
		// TODO Auto-generated method stub
		return classroomDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return classroomDao.countByName(name);
	}

	@Override
	public List<Classroom> listByName(String name) {
		// TODO Auto-generated method stub
		return classroomDao.listByName(name);
	}

	@Override
	public List<Classroom> listByName(String roomName, String gradeName) {
		// TODO Auto-generated method stub
		return classroomDao.listByName(roomName, gradeName);
	}

}
