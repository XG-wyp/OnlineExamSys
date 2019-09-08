package edu.prj.service;

import java.util.List;

import edu.prj.bean.PaperItem;

public interface PaperItemService {
	Long insert(PaperItem bean);

	Long delete(Long id);

	Long update(PaperItem bean);

	java.util.List<PaperItem> list();

	PaperItem load(Long id);

	Long count();

	PaperItem loadByName(String name);

	Long countByName(String name);

	List<PaperItem> listByName(String name);

	List<PaperItem> listByPaperId(Long paperId);
}
