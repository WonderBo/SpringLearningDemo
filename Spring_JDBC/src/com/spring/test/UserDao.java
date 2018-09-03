package com.spring.test;

import java.util.List;

public interface UserDao {
	public void save(User u);
	public void delete(int id);
	public void update(User u);
	public List list();
}
