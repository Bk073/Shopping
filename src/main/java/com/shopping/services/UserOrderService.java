package com.shopping.services;

import com.shopping.entity.UserOrder;

public interface UserOrderService {
	void addUserOrder(UserOrder userOrder);
	double getCustomerOrderGrandTotal(int cartId);
}
