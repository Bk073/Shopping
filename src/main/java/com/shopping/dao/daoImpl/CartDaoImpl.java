package com.shopping.dao.daoImpl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dao.CartDao;
import com.shopping.dao.CartItemDao;
import com.shopping.entity.Cart;
import com.shopping.entity.Product;
@Repository
@Transactional
public class CartDaoImpl implements CartDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CartItemDao cartItemDao;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public Cart getCartByCartId(int cartId) {
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(Cart.class,Integer.valueOf(cartId));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}

	public Cart validate(int cartId) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/*public void update(Orders order) {
		int orderId = order.getId();
		double grandTotal = orderItemDao.
		order.setTotalPrice(grandTotal);

		Session session = sessionFactory.openSession();
		session.saveOrUpdate(order);
		session.flush();
		session.close();
		
	}*/
	public void update(Cart cart){
		
	}

}
