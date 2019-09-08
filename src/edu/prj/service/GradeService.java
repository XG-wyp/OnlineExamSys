package edu.prj.service;

import java.util.List;

import edu.prj.bean.Grade;

public interface GradeService {
	Long insert(Grade bean);

	Long delete(Long id);

	Long update(Grade bean);

	java.util.List<Grade> list();

	Grade load(Long id);

	Long count();

	Grade loadByName(String name);

	Long countByName(String name);

	List<Grade> listByName(String name);

	List<Grade> listByName(String GradeName, String CreateBy);
}
