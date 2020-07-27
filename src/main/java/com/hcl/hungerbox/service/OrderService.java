package com.hcl.hungerbox.service;

import java.util.List;

import com.hcl.hungerbox.dto.OrderReqDto;
import com.hcl.hungerbox.dto.OrderResDto;

public interface OrderService {

	public OrderResDto placeOrder(OrderReqDto orderReqDto);
}
