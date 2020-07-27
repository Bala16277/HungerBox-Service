package com.hcl.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.hungerbox.entity.Vendor;


public interface VendorRepository extends JpaRepository<Vendor, Integer>{

	Vendor findByVendorName(String vendorName);

}
