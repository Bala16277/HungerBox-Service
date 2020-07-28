package com.hcl.hungerbox.service;

import com.hcl.hungerbox.dto.OrderReqDto;
import com.hcl.hungerbox.dto.OrderResDto;

public interface OrderService {

	public OrderResDto placeOrder(OrderReqDto orderReqDto);
}
