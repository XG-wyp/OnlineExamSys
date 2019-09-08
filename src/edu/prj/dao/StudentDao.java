package edu.prj.dao;

import java.util.List;

import edu.prj.bean.Manager;
import edu.prj.bean.Student;

public interface StudentDao {
	Long insert(Student bean);

	Long delete(Long id);

	Long update(Student bean);

	java.util.List<Student> list();

	Student load(Long id);

	Long count();

	Student loadByName(String name);

	Long countByName(String name);

	List<Student> listByName(String name);

	List<Student> listByName(String loginName, String nickName);

	boolean login(Student bean);
}
