package com.hcl.hungerbox.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.hungerbox.entity.Item;
import com.hcl.hungerbox.entity.Vendor;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

//	public List<Item> findByItemNameContainsAndVendorNameContains(String itemName, String vendorName);

	public Optional<List<Item>> findByItemNameContains(String itemName);

	public Optional<List<Item>> findByVendor(Vendor vendor);

	public Optional<List<Item>> findByItemNameContainsAndVendor(String itemName, Vendor vendor);

}
