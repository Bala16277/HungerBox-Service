package com.hcl.hungerbox.service;

import com.hcl.hungerbox.dto.ItemListResponseDto;
import com.hcl.hungerbox.exception.ItemNotFoundException;
import com.hcl.hungerbox.exception.NoDataException;
import com.hcl.hungerbox.exception.VendorNotFoundException;

public interface SearchService {
	
	public ItemListResponseDto searchMenu(String itemName, String vendorName) throws ItemNotFoundException, VendorNotFoundException, NoDataException;
	
	
}
