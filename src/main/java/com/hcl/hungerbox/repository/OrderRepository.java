package com.hcl.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.hungerbox.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
