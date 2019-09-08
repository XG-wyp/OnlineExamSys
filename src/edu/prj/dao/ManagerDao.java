package edu.prj.dao;

import java.util.List;

import edu.prj.bean.*;

public interface ManagerDao {
	Long insert(Manager bean);

	Long delete(Long id);

	Long update(Manager bean);

	java.util.List<Manager> list();

	Manager load(Long id);

	Long count();

	Manager loadByName(String name);

	Manager loadByName(String name, Integer type);

	Long countByName(String name);

	List<Manager> listByName(String name);

	List<Manager> listByName(String loginName, String nickName);

	boolean login(Manager bean);
}
