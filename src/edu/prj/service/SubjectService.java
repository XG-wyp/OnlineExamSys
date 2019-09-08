package edu.prj.service;

import java.util.List;

import edu.prj.bean.Subject;

public interface SubjectService {
	Long insert(Subject bean);

	Long delete(Long id);

	Long update(Subject bean);

	java.util.List<Subject> list();

	Subject load(Long id);

	Long count();

	Subject loadByName(String name);

	Long countByName(String name);

	List<Subject> listByName(String name);
}
