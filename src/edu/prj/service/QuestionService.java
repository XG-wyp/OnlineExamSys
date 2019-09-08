package edu.prj.service;

import java.util.List;

import edu.prj.bean.Question;

public interface QuestionService {
	Long insert(Question bean);

	Long delete(Long id);

	Long update(Question bean);

	java.util.List<Question> list();

	Question load(Long id);

	Long count();

	Long countBySubjectId(Long Id);

	Question loadByName(String name);

	Long countByName(String name);

	List<Question> listByName(String name);

	List<Question> listBySubjectId(Long subjectId);
	
	List<Question> list( String questionName, String subjectName);
}
