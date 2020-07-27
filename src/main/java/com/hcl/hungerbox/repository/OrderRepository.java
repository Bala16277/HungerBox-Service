package com.hcl.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.hungerbox.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
