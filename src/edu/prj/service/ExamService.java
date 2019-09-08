package edu.prj.service;

import java.util.List;

import edu.prj.bean.Exam;

public interface ExamService {
	Long insert(Exam bean);

	Long delete(Long id);

	Long update(Exam bean);

	java.util.List<Exam> list();

	Exam load(Long id,Long stuId);
	
	Exam load(Long id);

	Long count();

	Exam loadByName(String name);

	Exam loadById(Long id);

	Long countByName(String name);

	List<Exam> listByName(String name);
	
	List<Exam> listByStuId(Long stuId);
	
	List<Exam> listByName(String subjectName, String nickName);
}

