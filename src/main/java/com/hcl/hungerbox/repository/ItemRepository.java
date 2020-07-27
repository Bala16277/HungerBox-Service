package com.hcl.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.hungerbox.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	Item findByItemName(String itemName);

}
