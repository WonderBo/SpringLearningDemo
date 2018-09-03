package com.spring.test;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(User u) {
		// TODO Auto-generated method stub
		String sql="insert into UserTest values(?,?)";
		jdbcTemplate.update(sql, new Object[]{u.getName(),u.getPassword()});
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List list() {
		// TODO Auto-generated method stub
		return null;
	}

}
