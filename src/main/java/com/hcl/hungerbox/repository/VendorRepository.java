package com.hcl.hungerbox.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.hungerbox.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	
	public Optional<List<Vendor>> findByVendorNameContains(String vendorName);

//	public Vendor findByVendorNameContains(String vendorName);

}
