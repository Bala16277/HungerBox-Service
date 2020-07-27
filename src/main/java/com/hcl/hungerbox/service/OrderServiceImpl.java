package com.hcl.hungerbox.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.hungerbox.clients.AccountClient;
import com.hcl.hungerbox.dto.AccountDto;
import com.hcl.hungerbox.dto.OrderDto;
import com.hcl.hungerbox.dto.OrderReqDto;
import com.hcl.hungerbox.dto.OrderResDto;
import com.hcl.hungerbox.dto.TransactionResDto;
import com.hcl.hungerbox.entity.Item;
import com.hcl.hungerbox.entity.ItemOrder;
import com.hcl.hungerbox.entity.Orders;
import com.hcl.hungerbox.entity.User;
import com.hcl.hungerbox.entity.Vendor;
import com.hcl.hungerbox.repository.ItemOrderRepository;
import com.hcl.hungerbox.repository.ItemRepository;
import com.hcl.hungerbox.repository.OrdersRepository;
import com.hcl.hungerbox.repository.UserRepository;
import com.hcl.hungerbox.repository.VendorRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	AccountClient accountClient;

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	ItemOrderRepository itemOrderRepository;

	public OrderResDto placeOrder(OrderReqDto orderReqDto) {
		logger.info("inside order service:  ");
		OrderResDto orderResDto = new OrderResDto();
		String message = "Order placed successfully";
		List<OrderDto> requestOrder = orderReqDto.getItemList();

		double totalCost = 0.00;
		for (OrderDto order : requestOrder) {

			String itemName = order.getItemName();
			Item item = itemRepository.findByItemName(order.getItemName());
			totalCost = totalCost + (item.getItemCost() * order.getQuantity());
		}

		String userName = orderReqDto.getUserName();
		User user = userRepository.findByUserName(userName);

		AccountDto fromAccount = accountClient.getAccounts(Integer.parseInt(user.getPhone()));
		int toAccount = 58139;
		ResponseEntity<String> transfer = accountClient.fundTransfer(fromAccount.getAcountNumber(), toAccount, (int) totalCost);
		Orders orders = new Orders();
		orders.setTotalCost(totalCost);
		orders.setUser(user);
		ordersRepository.save(orders);

		Orders order1 = ordersRepository.findTopByOrderByOrderIdDesc();
		for (OrderDto order : requestOrder) {

			String itemName = order.getItemName();
			Item item = itemRepository.findByItemName(order.getItemName());
			saveItemOrder(item, order.getQuantity(), order1);
			// logger.info(verdor.getVendorDescription());
		}
		orderResDto.setMessage(message);
		orderResDto.setStatusCode(HttpStatus.CREATED.value());
		return orderResDto;

	}

	private void saveItemOrder(Item item, int quantity, Orders order1) {
		ItemOrder itemOrder = new ItemOrder();

		itemOrder.setItem(item);
		itemOrder.setQuantity(quantity);
		itemOrder.setOrder(order1);

		itemOrderRepository.save(itemOrder);
	}

	public List<TransactionResDto> getTransactions(String userName) {
		List<TransactionResDto> transactionResDtos = new ArrayList();
		
		User user = userRepository.findByUserName(userName);
		if(user!=null) {
			List<Orders> transactions = ordersRepository.findByUser(user);
			List<Orders> latestTransactions;
			if (transactions.size() >= 3) {
				latestTransactions = transactions.subList(transactions.size() - 2, transactions.size());
			} else {
				latestTransactions = transactions;
			}

			for (Orders transaction : latestTransactions) {
				TransactionResDto transactionResDto = new TransactionResDto();
				transactionResDto.setTotalCost(transaction.getTotalCost());
				transactionResDto.setOrderId(transaction.getOrderId());

				transactionResDtos.add(transactionResDto);
			}
		}else {
			
		}
		
		return transactionResDtos;

	}

}
