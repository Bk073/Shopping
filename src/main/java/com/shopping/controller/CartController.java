package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.dao.CartDao;
import com.shopping.entity.Cart;
import com.shopping.entity.Product;
import com.shopping.entity.UserOrder;
import com.shopping.services.CartService;
import com.shopping.services.UserService;


@Controller
@RequestMapping(value="/cart/")
public class CartController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@RequestMapping(value ="{cartId}", method = RequestMethod.GET)
	public ResponseEntity <Cart> getCart(@PathVariable("cartId") int cartId){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		com.shopping.entity.User user1 = userService.findByUserName(username);
		Cart cart = (Cart) user1.getCart().getCartItem();
		if(cart.equals(null)){
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cart> (cart,HttpStatus.OK);
	}
	
	@RequestMapping("/getCart/{cartId}")
	public @ResponseBody Cart getCartItems(@PathVariable(value="cartId")int cartId){
		return cartService.getCartByCartId(cartId);
	}
	
}