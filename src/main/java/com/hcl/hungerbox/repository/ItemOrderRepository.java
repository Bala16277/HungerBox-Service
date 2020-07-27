package com.hcl.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.hungerbox.entity.ItemOrder;


public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer>{

}
