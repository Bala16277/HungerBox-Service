package com.hcl.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.hungerbox.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	public Vendor findByVendorNameContains(String vendorName);

}
