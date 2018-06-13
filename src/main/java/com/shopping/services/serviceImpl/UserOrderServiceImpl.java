package com.shopping.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.UserOrderDao;
import com.shopping.entity.Cart;
import com.shopping.entity.CartItem;
import com.shopping.entity.UserOrder;
import com.shopping.services.CartService;
import com.shopping.services.UserOrderService;
@Service
public class UserOrderServiceImpl implements UserOrderService{
	@Autowired
	private UserOrderDao userOrderDao;
	@Autowired
	private CartService cartService;
	public void addUserOrder(UserOrder userOrder) {
		userOrderDao.addUserOrder(userOrder);	
		
	}

	public double getCustomerOrderGrandTotal(int cartId) {
		double grandTotal=0;
		Cart cart = cartService.getCartByCartId(cartId);
		List<CartItem> cartItems = cart.getCartItem();
		
		for(CartItem item: cartItems){
			grandTotal += item.getPrice();
		}
		return grandTotal;
	}

}
