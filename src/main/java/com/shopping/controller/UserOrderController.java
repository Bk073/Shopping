package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Cart;
import com.shopping.entity.Product;
import com.shopping.entity.User;
import com.shopping.entity.UserOrder;
import com.shopping.services.CartService;
import com.shopping.services.UserOrderService;

@RestController
@RequestMapping(value="/order/")
public class UserOrderController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserOrderService userOrderService;

	@RequestMapping(value="/{cartId}", method = RequestMethod.GET)
	public  ResponseEntity <UserOrder> createOrder(@PathVariable("cartId") int cartId) {

		UserOrder userOrder = new UserOrder();

		Cart cart = cartService.getCartByCartId(cartId);
		// Update CartId for customerOrder - set CartId
		userOrder.setcart(cart);

		User user1 = cart.getUser();

		userOrder.setUser(user1);	
		// Set customerid
		// Set ShippingAddressId
		userOrder.setShippingAddress(user1.getShippingAddress());

		userOrder.setBillingAddress(user1.getBillingAddress());

		userOrderService.addUserOrder(userOrder);

		return new ResponseEntity <UserOrder>(userOrder, HttpStatus.OK);
	}
}
