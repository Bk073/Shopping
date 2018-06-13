package com.shopping.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.CartDao;
import com.shopping.entity.Cart;
import com.shopping.services.CartService;
@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartDao cartDao;
	public Cart getCartByCartId(int cartId) {
			
		return cartDao.getCartByCartId(cartId);
	}

}
