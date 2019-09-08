package edu.prj.service;

import java.util.List;

import edu.prj.bean.ExamItem;

public interface ExamItemService {
	Long insert(ExamItem bean);

	Long delete(Long id);

	Long update(ExamItem bean);

	java.util.List<ExamItem> list();

	ExamItem load(Long id);

	Long count();

	Long countNull(Long ExamId);// 计算还未写答案的题目

	Long countTrue(Long ExamId);// 计算正确的题数

	Long countByExamId(Long ExamId);

	ExamItem loadByName(String name);

	ExamItem loadById(Long id);

	Long countByName(String name);

	List<ExamItem> listByName(String name);

	List<ExamItem> listByExamId(Long ExamId);
}
