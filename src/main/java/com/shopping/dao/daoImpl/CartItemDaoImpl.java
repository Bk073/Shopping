package com.shopping.dao.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dao.CartItemDao;
import com.shopping.entity.Cart;
import com.shopping.entity.CartItem;


@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addCartItem(CartItem cartItem) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(cartItem);
		session.flush();
		session.close();
	}

	public void removeCartItem(int i) {
		Session session = sessionFactory.openSession();
		CartItem cartItem = (CartItem) session.get(CartItem.class, i);
		session.delete(cartItem);
		Cart cart = cartItem.getCart();
		List<CartItem> cartItems = cart.getCartItem();
		cartItems.remove(cartItem);
		session.flush();
		session.close();
	}

	public void removeAllCartItems(Cart cart) {
		List<CartItem> cartItems = cart.getCartItem();
		for (CartItem cartItem : cartItems) {
			removeCartItem(cartItem.getCartItemId());
		}
	}

}