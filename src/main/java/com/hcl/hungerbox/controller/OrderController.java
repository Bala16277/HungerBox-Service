package com.hcl.hungerbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hungerbox.dto.OrderReqDto;
import com.hcl.hungerbox.dto.OrderResDto;
import com.hcl.hungerbox.dto.TransactionResDto;
import com.hcl.hungerbox.entity.Orders;
import com.hcl.hungerbox.service.OrderServiceImpl;


@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl orderService;
	
	@PostMapping("/order")
	public ResponseEntity<OrderResDto> placeOrder(@RequestBody OrderReqDto orderReqDto){
		OrderResDto resDto = orderService.placeOrder(orderReqDto);
		return new ResponseEntity<>(resDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionResDto>> getTransactions(@RequestParam String userName){
	List<TransactionResDto> transactions = orderService.getTransactions(userName);
		
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
}
