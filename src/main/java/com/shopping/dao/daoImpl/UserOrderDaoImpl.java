package com.shopping.dao.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dao.UserOrderDao;
import com.shopping.entity.UserOrder;
@Repository
@Transactional
public class UserOrderDaoImpl implements UserOrderDao{
	@Autowired
	private SessionFactory sessionFactory;
	public void addUserOrder(UserOrder userOrder) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(userOrder);
		session.flush();
		session.close();
	}

}
