package com.hcl.hungerbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.hungerbox.entity.Orders;
import com.hcl.hungerbox.entity.User;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	Orders findByTotalCostAndUser(double totalCost, User user);

	Orders findTopByOrderByOrderIdDesc();

	List<Orders> findByUser(User user);


}
