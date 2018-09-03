package com.spring.test;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UserDaoImpl extends HibernateTemplate implements UserDao {

	@Override
	public void save(User u) {
		// TODO Auto-generated method stub
//		String sql="insert into UserTest values(?,?)";
//		super.update(sql, new Object[]{u.getName(),u.getPassword()});
		super.save(u);
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
		String hql="from User";
		return super.find(hql);
	}

}
