package com.shopping.services;

import com.shopping.entity.Cart;
import com.shopping.entity.CartItem;

public interface CartItemService {
	void addCartItem(CartItem cartItem);
	void removeCartItem(int CartItemId);
	void removeAllCartItems(Cart cart);
}
