package com.shopping.dao;

import com.shopping.entity.Cart;
import com.shopping.entity.CartItem;

public interface CartItemDao {
	void addCartItem(CartItem cartItem);
	void removeCartItem(int CartItemId);
	void removeAllCartItems(Cart cart);

}
