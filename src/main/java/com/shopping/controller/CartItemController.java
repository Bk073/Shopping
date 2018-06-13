package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dao.ProductDao;
import com.shopping.entity.Cart;
import com.shopping.entity.CartItem;
import com.shopping.entity.Product;
import com.shopping.services.CartItemService;
import com.shopping.services.CartService;
import com.shopping.services.UserService;

@RestController
@RequestMapping(value="/cartItem/")

public class CartItemController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductDao productDao;

	
	public UserService getUserService() {
		return userService;
	}

	public void setCustomerService(UserService userService) {
		this.userService = userService;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public CartItemService getCartItemService() {
		return cartItemService;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	@RequestMapping(value ="add/{productName}" , method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addCartItem(@PathVariable(value = "productName") String productName) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		com.shopping.entity.User user1 = userService.findByUserName(username);
		//System.out.println("User : " + user1.getUsers().getEmailId());
		Cart cart = user1.getCart();
		System.out.println(cart);
		List<CartItem> cartItems = cart.getCartItem();
		Product product = productDao.getProductsByName(productName);
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			if (product.getProductName().equals(cartItem.getProduct().getProductId())) {
				cartItem.setQuality(cartItem.getQuality() + 1);
				cartItem.setPrice(cartItem.getQuality() * cartItem.getProduct().getProductPrice());
				cartItemService.addCartItem(cartItem);
				return;
			}
		}
		CartItem cartItem = new CartItem();
		cartItem.setQuality(1);
		cartItem.setProduct(product);
		cartItem.setPrice(product.getProductPrice() * 1);
		cartItem.setCart(cart);
		cartItemService.addCartItem(cartItem);
	}

	@RequestMapping(value= "removeCartItem/{cartItemId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeCartItem(@PathVariable(value = "cartItemId") String cartItemId) {
		cartItemService.removeCartItem(cartItemId);
	}

	@RequestMapping(value = "removeAllItems/{cartId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeAllCartItems(@PathVariable(value = "cartId") int cartId) {
		Cart cart = cartService.getCartByCartId(cartId);
		cartItemService.removeAllCartItems(cart);
	}

}