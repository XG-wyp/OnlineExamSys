package edu.prj.service;

import java.util.List;

import edu.prj.bean.Classroom;

public interface ClassroomService {
	Long insert(Classroom bean);

	Long delete(Long id);

	Long update(Classroom bean);

	java.util.List<Classroom> list();

	Classroom load(Long id);

	Long count();

	Classroom loadByName(String name);

	Long countByName(String name);

	List<Classroom> listByName(String name);

	List<Classroom> listByName(String roomName, String gradeName);
}

