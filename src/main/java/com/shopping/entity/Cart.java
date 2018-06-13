package com.shopping.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="cart")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="cart_id")
	private int Id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<CartItem> cartItem;
	
	private double totalPrice;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
