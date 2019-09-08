package edu.prj.dao;

import java.util.List;

import edu.prj.bean.Manager;
import edu.prj.bean.Teacher;

public interface TeacherDao {
	Long insert(Teacher bean);

	Long delete(Long id);

	Long update(Teacher bean);

	java.util.List<Teacher> list();

	Teacher load(Long id);

	Long count();

	Teacher loadByName(String name);

	Long countByName(String name);

	List<Teacher> listByName(String name);

	List<Teacher> listByName(String loginName, String nickName);

	boolean login(Teacher bean);

}
