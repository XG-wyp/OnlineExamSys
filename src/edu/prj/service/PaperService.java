package edu.prj.service;

import java.util.List;

import edu.prj.bean.Paper;

public interface PaperService {
	Long insert(Paper bean);

	Long delete(Long id);

	Long update(Paper bean);

	java.util.List<Paper> list();

	Paper load(Long id);

	Long count();

	Paper loadByName(String name);

	Paper loadById(Long id);

	Paper loadBy(Long id);

	Long countByName(String name);

	List<Paper> listByName(String name);

	List<Paper> listForStudent(String name);
}
